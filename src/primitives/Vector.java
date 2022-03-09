package primitives;

public class Vector extends Point{

    public void isItZero(){
        if (xyz.equals((Double3.ZERO))){
            throw new IllegalArgumentException("Invalid value cannot be zero!");
        }
    }

    @Override
    public String toString() {
        return "->" + super.toString();
    }

    public Vector(double x, double y, double z) {
        super(x, y, z);
        isItZero();
    }

    public Vector(Double3 xyz) {
        super(xyz);
        isItZero();
    }

    /**
     * Adds two vectors
     * @param v: represents a vector
     * @return a vector that is the addition between two vectors
     */
    public Vector add(Vector v) {
        return new Vector(
                xyz.d1 + v.xyz.d1,
                xyz.d2 + v.xyz.d2,
                xyz.d3 + v.xyz.d3);
    }

    /**
     * Multiplies a vector by a number
     * @param d: a scalar
     * @return a vector that is multiplied by the scalar
     */
    public Vector scale(double d) {
        return new Vector(
                xyz.d1 * d,
                xyz.d2 * d,
                xyz.d3 * d);
    }

    /**
     * Calculates the dot product between two vectors
     * @param v: represents a vector
     * @return the dot product between the vectors
     */
    public double dotProduct(Vector v){
        double product = 0;
        product += this.xyz.d1 * v.xyz.d1;
        product += this.xyz.d2 * v.xyz.d2;
        product += this.xyz.d3 * v.xyz.d3;
        return product;
    }

    /**
     * Calculates the cross product between two vectors
     * @param v: represents a vector
     * @return the cross product between the vectors
     */
    public Vector crossProduct(Vector v){
        return new Vector(
            this.xyz.d2 * v.xyz.d3 - this.xyz.d3 * v.xyz.d2,
            this.xyz.d3 * v.xyz.d1 - this.xyz.d1 * v.xyz.d3,
            this.xyz.d1 * v.xyz.d2 - this.xyz.d2 * v.xyz.d1);
    }

    /**
     * @return the squared value of the vector’s length
     */
    public double lengthSquared() {
        return xyz.d1 * xyz.d1 +
                xyz.d2 * xyz.d2 +
                xyz.d3 * xyz.d3;
    }

    /**
     * @return the length of the vector
     */
    public double length(){
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * @return a new vector which is the original vector, normalized
     */
    public Vector normalize() {
        return this.scale(1 / this.length());
    }
}
