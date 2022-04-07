package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.*;

import static primitives.Util.*;

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
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
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
        double vn1 = alignZero(rayDir.dotProduct((v1.crossProduct(v2)).normalize()));
        if (vn1 == 0) return null;

        Vector v3 = (rayP0.subtract(point2));
        double vn2 = alignZero(rayDir.dotProduct((v2.crossProduct(v3)).normalize()));
        if (vn1 * vn2 <= 0) return null;

        double vn3 = alignZero(rayDir.dotProduct((v3.crossProduct(v1)).normalize()));
        if (vn1 * vn3 <= 0) return null;

        return List.of(new GeoPoint(this,pointList.get(0)));
    }
}
