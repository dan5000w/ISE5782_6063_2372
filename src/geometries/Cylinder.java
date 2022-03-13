package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Plane Cylinder represents three-dimensional Cylinder in 3D Cartesian coordinate
 * system
 * @author DW, AC
 */
public class Cylinder extends Tube{

    private final double height;

    /**
     * Set values for Cylinder fields
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     * Method to print the class
     */
    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    /**
     * Get value of height
     */
    public double getHeight() {
        return height;
    }

    /**
     * {@link Geometry#getNormal(Point)} }
     */
    @Override
    public Vector getNormal(Point p) {
        Point a = p.add(axisRay.getDir());
        return p.subtract(a).normalize();
    }
}
