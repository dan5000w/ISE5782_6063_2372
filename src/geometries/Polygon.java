package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.*;

import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 *
 * @author Dan
 */
public class Polygon extends Geometry implements Boundable {
    /**
     * List of polygon's vertices
     */
    protected final List<Point> vertices;
    /**
     * Associated plane in which the polygon lays
     */
    protected final Plane plane;
    private final int size;

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     *
     * @param vertices list of vertices according to their order by edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same
     *                                  point
     *                                  <li>The vertices are not in the same
     *                                  plane</li>
     *                                  <li>The order of vertices is not according
     *                                  to edge path</li>
     *                                  <li>Three consequent vertices lay in the
     *                                  same line (180&#176; angle between two
     *                                  consequent edges)
     *                                  <li>The polygon is concave (not convex)</li>
     *                                  </ul>
     */
    public Polygon(Point... vertices) {
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        this.vertices = List.of(vertices);
        size = vertices.length;
        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (size == 3)
            return; // no need for more tests for a Triangle

        Vector n = plane.getNormal();

        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (var i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
    }

    @Override
    public Vector getNormal(Point point) {
        return plane.getNormal();
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return null;
    }

    @Override
    public AxisAlignedBoundingBox getAxisAlignedBoundingBox() {
        double minX, minY, minZ, maxX, maxY, maxZ;
        minX = maxX = vertices.get(0).getX();
        minY = maxY = vertices.get(0).getY();
        minZ = maxZ = vertices.get(0).getZ();
        //find the furthest coordinates
        for (int i = 1; i < vertices.size(); i++) {
            Point currentPoint = vertices.get(i);
            double currentX = currentPoint.getX();
            double currentY = currentPoint.getY();
            double currentZ = currentPoint.getZ();
            if (currentX > maxX)
                maxX = currentX;
            if (currentY > maxY)
                maxY = currentY;
            if (currentZ > maxZ)
                maxZ = currentZ;
            if (currentX < minX)
                minX = currentX;
            if (currentY < minY)
                minY = currentY;
            if (currentZ < minZ)
                minZ = currentZ;
        }
        AxisAlignedBoundingBox res = new AxisAlignedBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
        res.addToContains(this);

        return res;
    }
}
