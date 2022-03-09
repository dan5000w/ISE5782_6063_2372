package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Triangle class
 * @author Amitay Cahalon && Daniel Wolpert
 *
 */
class TriangleTests {


    /**
     * Test method for {@link Triangle#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        Point p1 = new Point(0,1,0);
        Plane plane = new Plane(p1, new Point(1,0, 0),  new Point(0,0,1));

        // ============ Equivalence Partitions Tests ==============
        // T01: Test that the getNormal function on Triangle is calculated correctly
        assertEquals(1,
                Math.abs(plane.getNormal(p1).dotProduct(new Vector(1,1,1).normalize())),
                0.00001, "Triangle getNormal function gives wrong normal.");
    }
}
