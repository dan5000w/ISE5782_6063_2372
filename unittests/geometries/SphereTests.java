package geometries;

import org.junit.jupiter.api.Test;
import primitives.Vector;
import primitives.Point;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Sphere class
 * @author Amitay Cahalon && Daniel Wolpert
 *
 */
class SphereTests {

    /**
     * Test method for {@link Sphere#getNormal(Point)} 
     */
    @Test
    void getNormal() {
        Sphere sphere = new Sphere(Point.ZERO, 1);

        // ============ Equivalence Partitions Tests ==============
        // T01: Test that the getNormal function on Sphere is calculated correctly
        Point p1 = new Point(0, 0, 1);
        assertEquals(sphere.getNormal(p1), new Vector(0, 0, 1),
                "Sphere getNormal function gives wrong normal.");
    }
}
