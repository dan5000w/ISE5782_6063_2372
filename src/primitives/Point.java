package primitives;

/**
 * Point class represents three-dimensional point in 3D Cartesian coordinate
 * system
 * @author DW, AC
 */
public class Point {

    protected Double3 xyz;

    protected Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Set values for point fields
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Method to check if our point is equals to the parameter o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Point)) return false;
        return xyz.equals(((Point) o).xyz);
    }

    /**
     * Method to print the class
     */
    @Override
    public String toString() {
        return xyz.toString();
    }

    /**
     * Adds vector to a point
     * @param v represents a vector
     * @return the point after adding the vector
     */
    public Point add(Vector v) {
        return new Point(this.xyz.add(v.xyz));
    }

    /**
     * vector subtraction
     * @param p represents a Point
     * @return a vector between two of the points
     */
    public Vector subtract(Point p) {
        return new Vector( this.xyz.subtract(p.xyz));
    }

    /**
     * Calculates the distance between two points, squared
     * @param p a point
     * @return  the distance, squared
     */
    public double distanceSquared(Point p) {
        double dx = p.xyz.d1 - this.xyz.d1;
        double dy = p.xyz.d2 - this.xyz.d2;
        double dz = p.xyz.d3 - this.xyz.d3;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Calculates the distance between two points
     * @param p a point
     * @return the distance
     */
    public double distance(Point p){
        return Math.sqrt(distanceSquared(p));
    }
}
