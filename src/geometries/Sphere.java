package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Sphere class represents three-dimensional Sphere in 3D Cartesian coordinate
 * system
 * @author DW, AC
 */
public class Sphere implements Geometry{

    protected final Point center;
    protected final double radius;

    /**
     * Set values for Sphere fields
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * Get value of center
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Get value of radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Method to print the class
     */
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

}
