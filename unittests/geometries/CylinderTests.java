package geometries;

import org.junit.jupiter.api.Test;
import primitives.Ray;
import primitives.Vector;
import primitives.Point;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Cylinder class
 *
 * @author Amitay Cahalon && Daniel Wolpert
 */
class CylinderTests {


    /**
     * Test method for {@link Cylinder#getNormal(Point)}.
     */
    @Test
    public void testGetNormal() {
        Cylinder cylinder = new Cylinder(new Ray(new Point(0, 0, -1), new Vector(0, 0, 1)), 2d, 2);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test normal to cylinder at side (also tests that normal length is 1)
        var normal = cylinder.getNormal(new Point(2, 0, 0));
        assertEquals(new Vector(1, 0, 0), normal, "Error: getNormal() doesn't work correctly.");

        // TC02: Test normal to cylinder at bottom base
        normal = cylinder.getNormal(new Point(1, 0, -1));
        assertEquals(new Vector(0, 0, -1), normal, "Error: getNormal() doesn't work correctly.");

        // TC03: Test normal to cylinder at top base
        normal = cylinder.getNormal(new Point(1, 0, 1));
        assertEquals(new Vector(0, 0, 1), normal, "Error: getNormal() doesn't work correctly.");

        // =============== Boundary Values Tests ==================
        // TC11: test normal at bottom base at center
        normal = cylinder.getNormal(new Point(0, 0, -1));
        assertEquals(new Vector(0, 0, -1), normal, "Error: getNormal() doesn't work correctly.");

        // TC12: test normal at top base at the center
        normal = cylinder.getNormal(new Point(0, 0, 1));
        assertEquals(new Vector(0, 0, 1), normal, "Error: getNormal() doesn't work correctly.");

    }
}
