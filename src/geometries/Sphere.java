package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;


/**
 * Sphere class represents three-dimensional Sphere in 3D Cartesian coordinate
 * system
 *
 * @author DW, AC
 */
public class Sphere implements Geometry {

    private final Point center;
    private final double radius;


    /**
     * Constructor using point and radius
     *
     * @param center the center point of the sphere
     * @param radius the radius of the sphere
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
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

    /**
     * {@link Geometry#getNormal(Point)} }
     */
    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getP0();
        Vector V = ray.getDir();
        if (p0.equals(center)) { //if the ray start is in the center of the sphere
            return List.of(ray.getPoint(radius));
        }
        Vector U = center.subtract(p0);
        double tm = V.dotProduct(U);
        double d = Math.sqrt((U.lengthSquared() - tm * tm));
        if (d >= radius) { //there is no intersections
            return null;
        }
        double th = Math.sqrt(radius * radius - d * d);
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        //if t1 or t2 is negative, the intersection is before the ray start
        if (t1 > 0 && t2 > 0) {
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);
            return List.of(p1, p2);
        }
        if (t1 > 0) {
            Point p1 = ray.getPoint(t1);
            return List.of(p1);
        }
        if (t2 > 0) {
            Point p2 = ray.getPoint(t2);
            return List.of(p2);
        }
        return null;
    }
}
