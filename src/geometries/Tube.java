package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Tube implements Geometry{

    protected final Ray axisRay;
    protected final double radius;

    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

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
