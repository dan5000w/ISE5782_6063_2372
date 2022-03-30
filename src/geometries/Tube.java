package geometries;

import primitives.*;
import primitives.Vector;

import java.util.*;

/**
 * Tube class represents three-dimensional tube in 3D Cartesian coordinate system
 *
 * @author Daniel Wolpert, Amitay Cahalon
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
    @Override
    public Vector getNormal(Point p) {
        Point center = axisRay.getPoint(axisRay.getDir().dotProduct(p.subtract(axisRay.getP0())));
        return (p.subtract(center)).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
