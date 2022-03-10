package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Plane class
 * @author Amitay Cahalon && Daniel Wolpert
 *
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
        Point p1 = new Point(0,1,0);
        Plane plane = new Plane(p1, new Point(1,0, 0),  new Point(0,0,1));

        // ============ Equivalence Partitions Tests ==============
        // T01: Test that the getNormal function on plane is calculated correctly
        assertEquals(1,
                Math.abs(plane.getNormal(p1).dotProduct(new Vector(1,1,1).normalize())),
                0.00001, "Plane getNormal function gives wrong normal.");
    }
}
