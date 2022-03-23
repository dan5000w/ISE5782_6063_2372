package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Plane class
 *
 * @author Amitay Cahalon && Daniel Wolpert
 */
class PlaneTests {

    /**
     * Test method for {@link Plane#Plane(Point, Point, Point)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // T01: Correct plane initialization
        try {
            new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Plane");
        }

        // =============== Boundary Values Tests ==================
        // T10: All points are on the same line
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(1, 1, 4), new Point(2, 2, 4), new Point(3, 3, 4)),
                "All points are on the same line!");

        // T11: The first and second points are equal
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(1, 1, 0), new Point(1, 1, 0), new Point(3, 3, 0)),
                "The first and second points are equal!");

        // T12: The third and second points are equal
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(4, 1, 5), new Point(2, 2, 0), new Point(2, 2, 0)),
                "The third and second points are equal!");

        // T13: The first and third points are equal
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(1, 1, 0), new Point(2, 4, 9), new Point(1, 1, 0)),
                "The first and third points are equal!");

    }

    /**
     * Test method for {@link Plane#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        Point p1 = new Point(0, 1, 0);
        Plane plane = new Plane(p1, new Point(1, 0, 0), new Point(0, 0, 1));

        // ============ Equivalence Partitions Tests ==============
        // T01: Test that the getNormal function on Triangle is calculated correctly
        assertEquals(1,
                Math.abs(plane.getNormal(p1).dotProduct(new Vector(1, 1, 1).normalize())),
                0.00001, "Triangle getNormal function gives wrong normal.");
    }

    /**
     * Test method for {@link Plane#findIntersections(Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Plane plane = new Plane(new Point(1, 0, 0), new Vector(0, 0, 1));
        // ============ Equivalence Partitions Tests ==============

        // T01: Ray intersects the plane
        Point p1 = new Point(1, 0, 0);
        List<Point> result1 = plane.findIntersections(new Ray(new Point(0, 0, 1),
                new Vector(1, 0, -1)));
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(List.of(p1), result1, "Ray crosses plane");

        // T02: Ray does not intersect the plane
        assertNull(plane.findIntersections(new Ray(new Point(1, 1, -5), new Vector(0, 1, 0))),
                "Ray's line out of plane");
        // =============== Boundary Values Tests ==================

        // T11: Ray is parallel to the plane (not included)
        assertNull(plane.findIntersections(new Ray(new Point(1, 1, -5), new Vector(2, 1, 0))),
                "Ray's line out of plane");

        // T12:  Ray is parallel to the plane (included)
        assertNull(plane.findIntersections(new Ray(new Point(1, 1, 0), new Vector(2, 1, 0))),
                "Ray's line out of plane");

        // T13: Ray is orthogonal to the plane (before the plane)
        Point p2 = new Point(1, 1, 0);
        List<Point> result2 = plane.findIntersections(new Ray(new Point(1, 1, 1),
                new Vector(0, 0, -1)));
        assertEquals(1, result2.size(), "Wrong number of points");
        assertEquals(List.of(p2), result2, "Ray crosses plane");

        // T14: Ray is orthogonal to the plane (after the plane)
        assertNull(plane.findIntersections(new Ray(new Point(1, 1, -5), new Vector(0, 0, -1))),
                "Ray's line out of plane");

        // T15: Ray is orthogonal to the plane (in the plane)
        assertNull(plane.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0, 0, -1))),
                "Ray's line out of plane");

        // T16: Ray is neither orthogonal nor parallel to and begins at the plane (𝑃0 is in the plane, but not the ray)
        assertNull(plane.findIntersections(new Ray(new Point(1, 1, 0), new Vector(0, 1, -1))),
                "Ray's line out of plane");

        // T17: Ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane (Q)
        assertNull(plane.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0, 1, -1))),
                "Ray's line out of plane");
    }
}
