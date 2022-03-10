package primitives;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Point class
 * @author Amitay Cahalon && Daniel Wolpert
 *
 */
class PointTests {
    /**
     * points for the tests
     */
    Point p1 = new Point(1, 2, 3);
    Point p2 = new Point(4, 5, 6);

    /**
     * Test method for {@link Point#add(Vector)}.
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============
        // T01: Test that point plus vector is calculated correctly
        assertEquals(p1.add(new Vector(-1, -2, -3)), Point.ZERO,
                "Point add function gave wrong answer");
    }

    /**
     * Test method for {@link Point#subtract(Point)}.
     */
    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============
        // T01: Test that point subtract another point is calculated correctly
        assertEquals((p1.subtract(p2)), new Vector(-3, -3, -3),
                "Point subtract function gives wrong answer.");
    }

    /**
     * Test method for {@link Point#distanceSquared(Point)}.
     */
    @Test
    void distanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        // T01: Test that the squared distance between two points is calculated correctly
        assertEquals(14, p1.distanceSquared(Point.ZERO), 0.00001,
                "Point distanceSquared function gives wrong distance.");
    }

    /**
     * Test method for {@link Point#distance(Point)}.
     */
    @Test
    void distance() {
        // ============ Equivalence Partitions Tests ==============
        // T01: Test that the distance between two points is calculated correctly
        assertEquals(Math.sqrt(14), p1.distance(Point.ZERO), 0.00001,
                "Point distance function gives wrong distance.");
    }
}
