package geometries;

import org.junit.jupiter.api.Test;
import primitives.Ray;
import primitives.Vector;
import primitives.Point;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Cylinder class
 * @author Amitay Cahalon && Daniel Wolpert
 *
 */
class CylinderTests {

    Ray r = new Ray(Point.ZERO, new Vector(0,0,1));
    Cylinder cylinder = new Cylinder(r, 1, 1);

    /**
     * Test method for {@link Cylinder#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        Point p1 = new Point(0,0.5,0);
        Vector option1 = (new Vector(0, 0, 1));
        Vector option2 = (new Vector(0, 0, -1));

        // T01: Test that the getNormal function on Cylinder works properly for points on side 1
        assertTrue(cylinder.getNormal(p1).equals(option1) || cylinder.getNormal(p1).equals(option2),
                "Cylinder getNormal function gives wrong normal.");

        // T02: Test that the getNormal function on Cylinder works properly for points on side 2
        Point p2 = new Point(0,0.5,1);
        assertTrue(cylinder.getNormal(p2).equals(option1) || cylinder.getNormal(p2).equals(option2),
                "Cylinder getNormal function gives wrong normal.");

        // =============== Boundary Values Tests ==================
        // T11: Test that the getNormal function on Cylinder works properly for points on side 1 rim
        Point p3 = new Point(0,1,0);
        assertTrue(cylinder.getNormal(p3).equals(option1) || cylinder.getNormal(p3).equals(option2),
                "Cylinder getNormal function gives wrong normal.");

        // T12: Test that the getNormal function on Cylinder works properly for points on side 2 rim
        Point p4 = new Point(0,1,1);
        assertTrue(cylinder.getNormal(p4).equals(option1) || cylinder.getNormal(p4).equals(option2),
                "Cylinder getNormal function gives wrong normal.");
    }
}