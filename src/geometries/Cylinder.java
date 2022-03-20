package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * Cylinder class represents three-dimensional Cylinder in 3D Cartesian coordinate
 * system
 *
 * @author DW, AC
 */
public class Cylinder extends Tube {

    private final double height;

    /**
     * Constructor of cylinder using ray, radius and height
     *
     * @param axisRay ray in the middle of the cylinder
     * @param radius  radius of the cylinder
     * @param height  height of the cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }


    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    /**
     * get the height of the cylinder
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * {@link Geometry#getNormal(Point)} }
     */
    @Override
    public Vector getNormal(Point p) {
        Point o1 = axisRay.getP0(); // middle of first end
        Point o2 = o1.add(axisRay.getDir().scale(height)); // middle of second end
        if (isZero(p.subtract(o1).dotProduct(axisRay.getDir())) || isZero(p.subtract(o2).dotProduct(axisRay.getDir()))) {
            Point a = p.add(axisRay.getDir());
            return p.subtract(a).normalize();
        }
        return super.getNormal(p);
    }
}
