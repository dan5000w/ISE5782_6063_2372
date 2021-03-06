package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.*;

import static primitives.Util.*;


/**
 * Plane class represents three-dimensional Plane in 3D Cartesian coordinate
 * system
 *
 * @author Daniel Wolpert, Amitay Cahalon
 */
public class Plane extends Geometry {
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

    @Override
    public Vector getNormal(Point p) {
        return normal;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        Vector rayDir = ray.getDir();
        Point rayP0 = ray.getP0();
        Vector u;
        try {
            u = p0.subtract(rayP0);
        } catch (IllegalArgumentException ignore) {
            return null;
        }
        double denominator = rayDir.dotProduct(normal);
        // if they are parallel
        if (isZero(denominator))
            return null;
        //t = n * (Q - Po) / n * v: t>0
        double t = alignZero(normal.dotProduct(u) / denominator);
        return t <= 0 || alignZero(t - maxDistance) > 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
    }

    public List<Vector> findVectorsOfPlane() {
        List<Vector> vectors = new LinkedList<>();
        double nX = this.getNormal().getX(), nY = this.getNormal().getY(), nZ = this.getNormal().getZ();
        double pX = this.getP0().getX(), pY = this.getP0().getY(), pZ = this.getP0().getZ();
        double d = -(nX * pX + nY * pY + nZ * pZ);
        Point p0 = this.getP0();
        int amount = 0;
        // we calculate a point on the plane, and then we create a vector with the point
        if (nX != 0) {
            double x1 = (d / nX);
            vectors.add((new Point(x1, 0, 0)).subtract(p0));
            amount++;
        }
        if (nY != 0) {
            double y2 = (d / nY);
            vectors.add((new Point(0, y2, 0)).subtract(p0));
            amount++;
        }
        if (nZ != 0 && amount < 2) {
            double z3 = (d / nZ);
            vectors.add((new Point(0, 0, z3)).subtract(p0));
        }
        return vectors;
    }
}

