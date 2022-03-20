package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Plane class represents three-dimensional Plane in 3D Cartesian coordinate
 * system
 *
 * @author DW, AC
 */
public class Plane implements Geometry {
    private final Point p0;
    private final Vector normal;


    /**
     * Constructor using point and normal vector
     *
     * @param p0     a point on the plane
     * @param normal the normal of the plane
     */
    public Plane(Point p0, Vector normal) {
        this.p0 = p0;
        this.normal = normal.normalize();
    }

    /**
     * Constructor using 3 point values
     *
     * @param p1 a point
     * @param p2 a point
     * @param p3 a point
     */
    public Plane(Point p1, Point p2, Point p3) {
        this.p0 = p1;

        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        Vector dir = v1.crossProduct(v2);

        this.normal = dir.normalize();
    }

    /**
     * Gets a point on the plane
     *
     * @return a point
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Gets the normal of the plane
     *
     * @return the normal vector
     */
    public Vector getNormal() {
        return normal;
    }


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

    @Override
    public List<Point> findIntersections(Ray ray) {
        Vector rayDir = ray.getDir();
        Point rayP0 = ray.getP0();
        // if they are parallel
        if (isZero(rayDir.dotProduct(normal)))
            return null;

        //t = n * (Q - Po) / n * v: t>0
        try {
            double t = alignZero(normal.dotProduct(p0.subtract(rayP0)) / normal.dotProduct(rayDir));
            if (t <= 0)
                return null;
            //p = P0 + t*v
            Point point = rayP0.add(rayDir.scale(t));
            List<Point> pointList = new LinkedList<>();
            pointList.add(point);
            return pointList;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
