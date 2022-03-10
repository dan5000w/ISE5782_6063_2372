package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Tube class
 * @author Amitay Cahalon && Daniel Wolpert
 *
 */
class TubeTests {

    Ray r = new Ray(Point.ZERO, new Vector(0, 0, 1));
    Tube tube = new Tube(r, 1);

    /**
     * Test method for {@link Tube#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // T01: Test that the getNormal function on Tube is calculated correctly
        Point p1 = new Point(0, 1, 0.5);
        assertEquals(new Vector(0, 1, 0), tube.getNormal(p1),
                "Tube getNormal function gives wrong normal.");


        // =============== Boundary Values Tests ==================
        // T11: Test that the getNormal function on Tube is calculated correctly for points on middle circle
        Point p3 = new Point(0, 1, 0);
        assertEquals(new Vector(0, 1, 0), tube.getNormal(p3),
                "Tube getNormal function  gives wrong normal.");
    }
}