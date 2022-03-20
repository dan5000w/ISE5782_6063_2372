package primitives;

/**
 * Ray class represents three-dimensional ray in 3D Cartesian coordinate
 * system
 *
 * @author DW, AC
 */
public class Ray {
    private final Point p0;
    private final Vector dir;

    /**
     * Constructor using point and vector
     *
     * @param p the starting point of the ray
     * @param v the direction vector of the ray
     */
    public Ray(Point p, Vector v) {
        p0 = p;
        dir = v.normalize();
    }

    /**
     * Gets the starting point of the ray
     *
     * @return the point
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Get value of direction vector
     *
     * @return the direction
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Calculates the point where starts at p0 and scaled by t.
     * @param t The scalar to scale the direction with.
     * @return The calculated point.
     */
    public Point getPoint(double t) {
        return p0.add(dir.scale(t));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Ray ray)) return false;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public String toString() {
        return "Ray: " +
                "p0=" + p0 +
                ", dir=" + dir;
    }
}
