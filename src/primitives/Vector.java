package primitives;

/**
 * Vector class represents three-dimensional vector int in 3D Cartesian coordinate
 * system
 * @author DW, AC
 */
public class Vector extends Point {

    /**
     * Method to print the class
     */
    @Override
    public String toString() {
        return "->" + super.toString();
    }

    /**
     * Method to check if our vector is equals to the parameter o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Vector)) return false;
        return xyz.equals(((Vector) o).xyz);
    }

    /**
     * Set values for vector fields
     */
    public Vector(double x, double y, double z) {
        this(new Double3(x, y, z));
    }

    Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals((Double3.ZERO)))
            throw new IllegalArgumentException("Invalid value cannot be zero!");
    }

    /**
     * Adds two vectors
     * @param v represents a vector
     * @return the result vector
     */
    public Vector add(Vector v) {
        return new Vector(this.xyz.add(v.xyz));
    }

    /**
     * Multiplies a vector by a number
     * @param d a scalar
     * @return a vector that is multiplied by the scalar
     */
    public Vector scale(double d) {
        return new Vector(this.xyz.scale(d));
    }

    /**
     * Calculates the dot product between two vectors
     * @param v represents a vector
     * @return the dot product between the vectors
     */
    public double dotProduct(Vector v) {
        double product = 0;
        product += this.xyz.d1 * v.xyz.d1;
        product += this.xyz.d2 * v.xyz.d2;
        product += this.xyz.d3 * v.xyz.d3;
        return product;
    }

    /**
     * Calculates the cross product between two vectors
     * @param v represents a vector
     * @return the cross product between the vectors
     */
    public Vector crossProduct(Vector v) {
        return new Vector(
                this.xyz.d2 * v.xyz.d3 - this.xyz.d3 * v.xyz.d2,
                this.xyz.d3 * v.xyz.d1 - this.xyz.d1 * v.xyz.d3,
                this.xyz.d1 * v.xyz.d2 - this.xyz.d2 * v.xyz.d1);
    }

    /**
     * Calculates the squared value of the vector’s length
     * @return the squared length
     */
    public double lengthSquared() {
        return dotProduct(this);
    }

    /**
     * Calculates the length of the vector
     * @return the length
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * Calculates the normalized vector
     * @return the normalized vector
     */
    public Vector normalize() {
        return this.scale(1 / this.length());
    }
}
