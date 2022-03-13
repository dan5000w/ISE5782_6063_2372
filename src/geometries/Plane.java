package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Plane class represents three-dimensional Plane in 3D Cartesian coordinate
 * system
 * @author DW, AC
 */
public class Plane implements Geometry {
    private final Point p0;
    private final Vector normal;

    /**
     * Set values for Plane fields when gets a point and a vector
     */
    public Plane(Point p0, Vector normal) {
        this.p0 = p0;
        this.normal = normal.normalize();
    }

    /**
     * Set values for Plane fields when gets three points
     */
    public Plane(Point p1, Point p2, Point p3) {
        this.p0 = p1;

        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        Vector dir = v1.crossProduct(v2);

        this.normal = dir.normalize();
    }

    /**
     * Get value of p0
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Get value of normal
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * Method to print the class
     */
    @Override
    public String toString() {
        return "Plane{" +
                "p0=" + p0 +
                ", normal=" + normal +
                '}';
    }

    /**
     * {@link Geometry#getNormal(Point)} }
     */
    @Override
    public Vector getNormal(Point p) {
        return normal;
    }
}
