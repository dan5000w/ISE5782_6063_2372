package primitives;

import static primitives.Util.isZero;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for primitives.Vector class
 * @author Amitay Cahalon && Daniel Wolpert
 */
class VectorTests {
    /**
     * Vectors for the tests
     */
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(0, 3, -2);
    Vector v3 = new Vector(-2, -4, -6);

    /**
     * Test method for {@link Vector#Vector(double, double, double)}.
     */
    @Test
    void testConstructor1() {
        // ============ Equivalence Partitions Tests ==============
        //T01: check constructor throws an exception properly
        assertThrows(
                IllegalArgumentException.class, () -> new Vector(0, 0, 0),
                "testConstructor() for zero-vector does not throw an exception");

    }
    /**
     * Test method for {@link Vector#Vector(Double3)}.
     */
    @Test
    void testConstructor2() {
        // ============ Equivalence Partitions Tests ==============
        //T01: check the standard addition is calculated correctly
        assertThrows(
                IllegalArgumentException.class, () -> new Vector(Double3.ZERO),
                "testConstructor() for zero-vector does not throw an exception");

    }

    /**
     * Test method for {@link Vector#add(Vector)}.
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============
        //T01: check the standard addition is calculated correctly
        assertEquals((v1.add(v2)), new Vector(1,5,1),"The standard addition does not work!");
    }

    /**
     * Test method for {@link Vector#scale(double)}.
     */
    @Test
    void scale() {
        // ============ Equivalence Partitions Tests ==============
        //T11: check that the new vector has a scaled length
        assertEquals((v1.add(v2)), new Vector(1,5,1),"The scale does not work!");

        // =============== Boundary Values Tests ==================
        //T12: check the addition with zero is calculated correctly
        assertThrows(IllegalArgumentException.class,() -> v1.scale(0), "The scale by zero does not work!");
    }

    /**
     * Test method for {@link Vector#dotProduct(Vector)}.
     */
    @Test
    void dotProduct() {

        // ============ Equivalence Partitions Tests ==============
        // T01: Test that dot product return value is calculated correctly
        assertEquals(-28, v1.dotProduct(v3), 0.00001, "The dot product does not work!");
        // T02: test that orthogonal vectors return 0 on dot product
        assertTrue(isZero(v1.dotProduct(v2)), "The dot product of orthogonal vectors does not work!");

    }

    /**
     * Test method for {@link Vector#crossProduct(Vector)}.
     */
    @Test
    void crossProduct() {

        // ============ Equivalence Partitions Tests ==============
        Vector crossProductResult = v1.crossProduct(v2);

        // T01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals( v1.length() * v2.length(), crossProductResult.length(), 0.00001,
                "crossProduct function returned wrong result length");

        // T02: Test cross-product result orthogonality to its operands
        assertTrue( isZero(crossProductResult.dotProduct(v1)),
                "crossProduct function result is not orthogonal to 1st operand");
        assertTrue( isZero(crossProductResult.dotProduct(v2)),
                "crossProduct function result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // T11: test zero vector from cross-product of co-lined vectors
        assertThrows(
                IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link Vector#lengthSquared()}.
     */
    @Test
    void lengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // T01: Test that lengthSquared function is calculated correctly
        assertEquals (14, v1.lengthSquared(), 0.00001, "lengthSquared function gave wrong value");

    }

    /**
     * Test method for {@link Vector#length()}.
     */
    @Test
    void length() {
        // ============ Equivalence Partitions Tests ==============
        // T01: Test that length value is calculated correctly
        assertEquals (Math.sqrt(14), v1.length(), 0.00001, "length function gave wrong value");
    }

    /**
     * Test method for {@link Vector#normalize()}.
     */
    @Test
    void normalize() {
        // test vector normalization vs vector length and cross-product
        Vector vNormal1 = v1.normalize();

        // ============ Equivalence Partitions Tests ==============
        // T01: Test that normalize makes the vector length 1
        assertEquals(1, vNormal1.length(), 0.0001,
                "normalize does not make the vector length 1");
    }
}
