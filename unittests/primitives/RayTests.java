package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RayTests {
    /**
     * Test method for {@link Ray#getPoint(double)}.
     */
    @Test
    void testGetPoint() {
        // ============ Equivalence Partitions Tests ==============
        // T01: Test that the point that starts at p0 and scaled by t (positive) is calculated correctly
        Point p0 = new Point(1, 0, 0);
        Ray ray = new Ray(p0, new Vector(0, 5, 0));
        assertEquals(new Point(1, 5, 0), ray.getPoint(5),
                "Ray getPoint function gives wrong distance!");

        // T02: Test that the point that starts at p0 and scaled by t (negative) is calculated correctly
        assertEquals(new Point(1, -5, 0), ray.getPoint(-5),
                "Ray getPoint function gives wrong distance!");

        // =============== Boundary Values Tests ==================
        // T11: Test that the point that starts at p0 and scaled by 0 is calculated correctly
        assertEquals(p0, ray.getPoint(0),
                "Ray getPoint function gives wrong distance!");
    }
}
