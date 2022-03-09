package primitives;

import java.util.Objects;

/**
 * Point class represents three-dimensional point in 3D Cartesian coordinate
 * system
 * @author DW, AC
 */
public class Point {

    public static Point ZERO = new Point(0,0,0);

    protected Double3 xyz;

    public Point(Double3 xyz) {
        this.xyz = xyz;
    }
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        return xyz.equals(((Point) o).xyz);
    }

    @Override
    public String toString() {
        return xyz.toString();
    }

    /**
     * Adds vector to a point
     * @param v: represents a vector
     * @return the point after adding the vector
     */
    public Point add(Vector v) {
        return new Point(this.xyz.d1 + v.xyz.d1,
                this.xyz.d2 + v.xyz.d2,
                this.xyz.d3 + v.xyz.d3);
    }

    /**
     * vector subtraction
     * @param p: represents a Point
     * @return a vector between two of the points
     */
    public Vector subtract(Point p) {
        return new Vector(this.xyz.d1 - p.xyz.d1,
                this.xyz.d2 - p.xyz.d2,
                this.xyz.d3 - p.xyz.d3);
    }

    /**
     * Calculates the distance between two points, squared
     * @param p: a point
     * @return  the distance between the points, squared
     */
    public double distanceSquared(Point p) {
        return (p.xyz.d1 - this.xyz.d1) * (p.xyz.d1 - this.xyz.d1) +
                (p.xyz.d2 - this.xyz.d2) * (p.xyz.d2 - this.xyz.d2) +
                (p.xyz.d3 - this.xyz.d3) * (p.xyz.d3 - this.xyz.d3);
    }

    /**
     * Calculates the distance between two points
     * @param p: a point
     * @return the distance between the points
     */
    public double distance(Point p){
        return Math.sqrt(distanceSquared(p));
    }
}

