package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

    /**
     * Test method for {@link Ray#findClosestPoint(List)}.
     */
    @Test
    void findClosestPoint() {
        // ============ Equivalence Partitions Tests ==============
        // T01: The point in the middle of the list is closest to the beginning of the ray
        Ray ray = new Ray(new Point(1, 0, 0), new Vector(0, 5, 0));
        assertEquals(new Point(1, 2, 0.5), ray.findClosestPoint(List.of(new Point(6, 9, 7), new Point(1, 2, 0.5), new Point(-8, 5, -19))),
                "Ray findClosestPoint function returns wrong value!");

        // =============== Boundary Values Tests ==================
        // T11: The point in the middle of the list is closest to the beginning of the ray
        assertNull(ray.findClosestPoint(null),
                "Ray findClosestPoint function returns wrong value!");

        // T12: The point that is the first element of the list is closest to the beginning of the ray
        assertEquals(new Point(1, 2, 0.5), ray.findClosestPoint(List.of(new Point(1, 2, 0.5), new Point(9, -2, 3), new Point(-8, 5, -19))),
                "Ray findClosestPoint function returns wrong value!");

        // T13: The point that is the last element of the list is closest to the beginning of the ray
        assertEquals(new Point(1, 2, 0.5), ray.findClosestPoint(List.of(new Point(16, -1, -3), new Point(9, -2, 3), new Point(1, 2, 0.5))),
                "Ray findClosestPoint function returns wrong value!");
    }
}
