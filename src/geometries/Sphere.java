package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.*;

import static primitives.Util.*;


/**
 * Sphere class represents three-dimensional Sphere in 3D Cartesian coordinate
 * system
 *
 * @author Daniel Wolpert, Amitay Cahalon
 */
public class Sphere extends Geometry {

    private final Point center;
    private final double radius;
    private final double radius2;


    /**
     * Constructor using point and radius
     *
     * @param center the center point of the sphere
     * @param radius the radius of the sphere
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
        this.radius2 = radius * radius;
    }

    /**
     * Gets the center point of the sphere
     *
     * @return the point
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Gets the radius of the sphere
     *
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }


    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center).normalize();
    }


    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        Vector u;
        try {
            u = center.subtract(ray.getP0());
        } catch (IllegalArgumentException ex) { // ray starts at center
            LinkedList<GeoPoint> res = new LinkedList<>();
            res.add(new GeoPoint(this,ray.getPoint(radius)));
            return res;
        }
        double tM = u.dotProduct(ray.getDir()); // projection of u on v.
        double d = Math.sqrt(u.lengthSquared() - (tM*tM));

        if (d-radius >= 0) return null; // ray is outside of sphere

        double tH = Math.sqrt(radius*radius - d*d);
        double t1 = alignZero(tM + tH);
        double t2 = alignZero(tM - tH);

        LinkedList<GeoPoint> res;
        if((t1 > 0 && alignZero(t1-maxDistance) <= 0) //
                || (t2 > 0 && alignZero(t2-maxDistance) <= 0)) { // this is done to not initialize for no reason.
            res = new LinkedList<>();
            if (t1 > 0 && alignZero(t1-maxDistance) <= 0) {
                Point p1 = ray.getPoint(t1);
                res.add(new GeoPoint(this,p1));
            }
            if (t2 > 0 && alignZero(t2-maxDistance) <= 0) {
                Point p2 = ray.getPoint(t2);
                res.add(new GeoPoint(this,p2));
            }
        } else return null;

        return res;
    }
}
