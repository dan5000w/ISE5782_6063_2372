package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Triangle class represents three-dimensional Triangle in 3D Cartesian coordinate
 * system
 *
 * @author DW, AC
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
        List<Point> pointList = plane.findIntersections(ray);
        if (pointList != null) {
            Point v0 = vertices.get(0);
            Point v1 = vertices.get(1);
            Point v2 = vertices.get(2);
            Point P = pointList.get(0);
            Vector N = plane.getNormal();
            // inside-outside test
            Vector C; // vector perpendicular to triangle's plane

            try {
                // edge 0
                Vector edge0 = v1.subtract(v0);
                Vector vp0 = P.subtract(v0);
                C = edge0.crossProduct(vp0);
                if (N.dotProduct(C) < 0) return null; // P is on the right side

                // edge 1
                Vector edge1 = v2.subtract(v1);
                Vector vp1 = P.subtract(v1);
                C = edge1.crossProduct(vp1);
                if ((N.dotProduct(C)) < 0) return null; // P is on the right side

                // edge 2
                Vector edge2 = v0.subtract(v2);
                Vector vp2 = P.subtract(v2);
                C = edge2.crossProduct(vp2);
                if ((N.dotProduct(C)) < 0) return null; // P is on the right side;
            } catch (IllegalArgumentException e) {
                return null;
            }

        }
        return pointList;
    }
}
