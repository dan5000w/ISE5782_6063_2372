package geometries;

import primitives.*;
import primitives.Vector;

import java.util.*;

import static primitives.Util.*;

/**
 * Tube class represents three-dimensional tube in 3D Cartesian coordinate system
 *
 * @author Daniel Wolpert, Amitay Cahalon
 */
public class Tube extends Geometry {
    /**
     * the ray of the tube
     */
    protected final Ray axisRay;

    /**
     * the radius of the tube
     */
    protected final double radius;

    /**
     * Constructor of tube using ray and radius
     *
     * @param axisRay ray in the middle of the tube
     * @param radius  radius of the tube
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    /**
     * Gets the ray of the tube
     *
     * @return the ray
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * get the radius of the tube
     *
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point p) {
        Point center = axisRay.getPoint(axisRay.getDir().dotProduct(p.subtract(axisRay.getP0())));
        return (p.subtract(center)).normalize();
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance) {
        Vector d = ray.getDir();
        Vector v = axisRay.getDir();
        double dv = d.dotProduct(v);

        if (ray.getP0().equals(axisRay.getP0())) {
            if (isZero(dv)) {
                if (alignZero(maxDistance - radius) > 0)
                    return List.of(new GeoPoint(this, ray.getPoint(radius)));
                else
                    return null;
            }

            Vector dvv = v.scale(d.dotProduct(v));

            if (d.equals(dvv)) {
                return null;
            }
            double t = Math.sqrt(radius * radius / d.subtract(dvv).lengthSquared());
            if(alignZero(maxDistance-t) > 0)
                return List.of(new GeoPoint(this, ray.getPoint(t)));
            else
                return null;
        }

        Vector x = ray.getP0().subtract(axisRay.getP0());

        double xv = x.dotProduct(v);

        double a = 1 - dv * dv;
        double b = 2 * d.dotProduct(x) - 2 * dv * xv;
        double c = x.lengthSquared() - xv * xv - radius * radius;

        if (isZero(a)) {
            if (isZero(b)) {
                return null;
            }
            double t = -c / b;
            if(alignZero(maxDistance-t) > 0)
                return List.of(new GeoPoint(this, ray.getPoint(t)));
            else
                return null;
        }

        double delta = alignZero(b * b - 4 * a * c);

        if (delta <= 0)
            return null;

        List<GeoPoint> intersections = null;
        double t = alignZero(-(b + Math.sqrt(delta)) / (2 * a));
        if (t > 0 && alignZero(maxDistance-t) > 0) {
            intersections = new LinkedList<>();
            intersections.add(new GeoPoint(this, ray.getPoint(t)));
        }
        t = alignZero(-(b - Math.sqrt(delta)) / (2 * a));
        if (t > 0 && alignZero(maxDistance-t) > 0) {
            if (intersections == null) {
                intersections = new LinkedList<>();
            }
            intersections.add(new GeoPoint(this, ray.getPoint(t)));
        }

        return intersections;
    }
}
