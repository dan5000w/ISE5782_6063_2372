package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.*;

/**
 * Cylinder class represents three-dimensional Cylinder in 3D Cartesian coordinate
 * system
 *
 * @author Daniel Wolpert, Amitay Cahalon
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

    @Override
    public Vector getNormal(Point p) {
        // we assume point is on the cylinder, so we check if it is on top, bottom or side.
        Vector rayDir = axisRay.getDir();
        Point centerBottom = axisRay.getP0();
        Point centerTop = axisRay.getPoint(height);
        if(p.equals(centerTop) || isZero(p.subtract(centerTop).dotProduct(rayDir))) { // on the top
            return rayDir;
        } else if (p.equals(centerBottom) || isZero(p.subtract(centerBottom).dotProduct(rayDir))) { //on the bottom
            return rayDir.scale(-1);
        } else { // on the side
            return super.getNormal(p);
        }
    }
}
