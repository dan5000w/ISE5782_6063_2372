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
public class Sphere implements Geometry {

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
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        if (p0.equals(center)) //if the ray start is in the center of the sphere
            return List.of(ray.getPoint(radius));

        Vector u = center.subtract(p0);
        double tm = v.dotProduct(u);
        double d2 = u.lengthSquared() - tm * tm;
        double th2 = alignZero(radius2 - d2);

        if (th2 <= 0) //there is no intersections
            return null;
        double th = Math.sqrt(th2);

        double t2 = alignZero(tm + th);
        if (t2 <= 0) return null; // both are behind the ray
        double t1 = alignZero(tm - th);
        return t1 <= 0 ? List.of(ray.getPoint(t2)) : List.of(ray.getPoint(t1), ray.getPoint(t2));
    }
}
