package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Tube class represents three-dimensional tube in 3D Cartesian coordinate
 * system
 * @author DW, AC
 */
public class Tube implements Geometry{

    protected final Ray axisRay;
    protected final double radius;

    /**
     * Set values for tube fields
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    /**
     * Get value of axisRay
     */
    public Ray getAxisRay() {
        return axisRay;
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
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    /**
     * {@link Geometry#getNormal(Point)} }
     */
    @Override
    public Vector getNormal(Point p) {
        Point center;
        //if point is the starting point of the ray
        try{
            //t = v * (p - p0)
            double t = axisRay.getDir().dotProduct(p.subtract(axisRay.getP0()));
            //center = p0 + t * v
            center = axisRay.getP0().add(axisRay.getDir().scale(t));
        }
        catch (Exception IllegalArgumentException) {
            center = axisRay.getP0();
        }
        return (p.subtract(center)).normalize();
    }
}
