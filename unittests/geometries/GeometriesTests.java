package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for geometries.Cylinder class
 *
 * @author Amitay Cahalon && Daniel Wolpert
 */
public class GeometriesTests {

    @Test
    public void findIntersectionsTest() {

        // ============ Equivalence Partitions Tests ==============
        // T01:	some shapes have an intersection point/s but not all of them
        Triangle triangle = new Triangle(new Point(1, 0, 0), new Point(1, 3, 0),
                new Point(5, 0, 0));
        Sphere sphere = new Sphere(new Point(-2, 0, 0), 1);
        Plane plane = new Plane(new Point(-6, -2, 0), new Vector(0, 0, 1));
        Geometries geometries = new Geometries(triangle, sphere, plane);
        List<Point> result1 = geometries.findIntersections(new Ray(new Point(-2, 0, 0),
                new Vector(0, 0, -1)));
        assertEquals(3, result1.size(), "Wrong number of points");

        // =============== Boundary Values Tests ==================
        // T11: checks find intersections on an empty collection
        Geometries geometries1 = new Geometries();
        assertNull(geometries1.findIntersections(new Ray(new Point(0, 2, 0), new Vector(0, 1, 0))),
                "Intersections found on an empty collection");
        // T12: no shape intersects with a body
        assertNull(geometries.findIntersections(new Ray(new Point(0, 0, 5), new Vector(1, 0, 0))),
                "Intersections found");
        // T13: only one shape intersects
        Geometries geometries2 = new Geometries(triangle, sphere);
        List<Point> result2 = geometries2.findIntersections(new Ray(new Point(-2, 0, 0),
                new Vector(0, 0, -1)));
        assertEquals(2, result2.size(), "Wrong number of points");
        // T14: all shapes intersects

        Geometries geometries3 = new Geometries(triangle, new Sphere(new Point(0.5, 0, 0), 1));
        List<Point> result3 = geometries3.findIntersections(new Ray(new Point(1, 0, 0),
                new Vector(0, 0, -1)));
        assertEquals(3, result3.size(), "Wrong number of points");
    }
}
