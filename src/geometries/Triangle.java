package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * Triangle class represents three-dimensional Triangle in 3D Cartesian coordinate
 * system
 *
 * @author Daniel Wolpert, Amitay Cahalon
 */
public class Triangle extends Polygon {

    /**
     * Constructor using 3 points
     *
     * @param p1 a point that is a vertex of the triangle
     * @param p2 a point that is a vertex of the triangle
     * @param p3 a point that is a vertex of the triangle
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        // First check the intersections with the plane
        Point point0 = vertices.get(0);
        Point point1 = vertices.get(1);
        Point point2 = vertices.get(2);

        Vector normal = (point0.subtract(point1).crossProduct(point1.subtract(point2))).normalize();
        Plane plane = new Plane(point0, normal);
        List<Point> pointList = plane.findIntersections(ray);
        if (pointList == null)
            return null;

        Point rayP0 = ray.getP0();
        Vector rayDir = ray.getDir();
        // After check if they're in the triangle
        Vector v1 = (rayP0.subtract(point0));
        Vector v2 = (rayP0.subtract(point1));
        Vector v3 = (rayP0.subtract(point2));
        Vector n1 = (v1.crossProduct(v2)).normalize();
        Vector n2 = (v2.crossProduct(v3)).normalize();
        Vector n3 = (v3.crossProduct(v1)).normalize();

        double vn1 = Util.alignZero(rayDir.dotProduct(n1));
        double vn2 = Util.alignZero(rayDir.dotProduct(n2));
        double vn3 = Util.alignZero(rayDir.dotProduct(n3));
        if (vn1 == 0 || vn2 == 0 || vn3 == 0)
            return null;

        if ((vn1 > 0 && vn2 > 0 && vn3 > 0) || (vn1 < 0 && vn2 < 0 && vn3 < 0)) {
            return pointList;
        }
        return null;
    }
}
