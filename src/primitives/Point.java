package primitives;

/**
 * Point class represents three-dimensional point in 3D Cartesian coordinate
 * system
 *
 * @author Daniel Wolpert, Amitay Cahalon
 */
public class Point {
    /**
     * Coordinates of the point
     */
    protected final Double3 xyz;

    /**
     * Point of the center of our Cartesian coordinate system
     */
    public static final Point ZERO = new Point(Double3.ZERO);

    /**
     * Constructor using coordinates
     *
     * @param xyz the coordinates of the point
     */
    protected Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Constructor using 3D coordinates values
     *
     * @param x coordinate value
     * @param y coordinate value
     * @param z coordinate value
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Gets the x coordinate of the point
     *
     * @return the coordinate
     */
    public double getX() {
        return xyz.d1;
    }

    /**
     * Gets the y coordinate of the point
     *
     * @return the coordinate
     */
    public double getY() {
        return xyz.d2;
    }

    /**
     * Gets the z coordinate of the point
     *
     * @return the coordinate
     */
    public double getZ() {
        return xyz.d3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Point)) return false;
        return xyz.equals(((Point) o).xyz);
    }

    @Override
    public String toString() {
        return xyz.toString();
    }

    /**
     * Adds vector to a point
     *
     * @param v other vector
     * @return the point after adding the vector
     */
    public Point add(Vector v) {
        return new Point(this.xyz.add(v.xyz));
    }

    /**
     * vector subtraction
     *
     * @param p other Point
     * @return a vector between two of the points
     */
    public Vector subtract(Point p) {
        return new Vector(this.xyz.subtract(p.xyz));
    }

    /**
     * Calculates the distance between two points, squared
     *
     * @param p other point
     * @return the distance, squared
     */
    public double distanceSquared(Point p) {
        double dx = p.xyz.d1 - this.xyz.d1;
        double dy = p.xyz.d2 - this.xyz.d2;
        double dz = p.xyz.d3 - this.xyz.d3;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Calculates the distance between two points
     *
     * @param p other point
     * @return the distance
     */
    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }
}
