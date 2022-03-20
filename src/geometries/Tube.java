package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Tube class represents three-dimensional tube in 3D Cartesian coordinate
 * system
 *
 * @author DW, AC
 */
public class Tube implements Geometry {
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

    /**
     * {@link Geometry#getNormal(Point)} }
     */
    @Override
    public Vector getNormal(Point p) {
        Point center;
        Vector dir = axisRay.getDir();
        Point p0 = axisRay.getP0();
        //if point is the starting point of the ray
        try {
            //t = v * (p - p0)
            double t = dir.dotProduct(p.subtract(p0));
            //center = p0 + t * v
            center = p0.add(dir.scale(t));
        } catch (Exception IllegalArgumentException) {
            center = p0;
        }
        return (p.subtract(center)).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
