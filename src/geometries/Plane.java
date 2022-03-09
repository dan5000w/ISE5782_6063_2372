package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
    private final Point p0;
    private final Vector normal;

    public Point getP0() {
        return p0;
    }

    public Vector getMYNormal() {
        return normal;
    }

    public Plane(Point p0, Vector normal) {
        this.p0 = p0;
        this.normal = normal.normalize();
    }

    public Plane(Point p1, Point p2, Point p3) {
        this.p0 = p1;

          Vector v1 = p2.subtract(p1);
          Vector v2 = p3.subtract(p1);
          Vector dir = v1.crossProduct(v2);

        this.normal = dir.normalize();
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
}
