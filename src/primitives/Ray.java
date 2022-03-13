package primitives;

/**
 * Ray class represents three-dimensional ray in 3D Cartesian coordinate
 * system
 * @author DW, AC
 */
public class Ray {
    private final Point p0;
    private final Vector dir;

    /**
     * Set values for ray fields
     */
    public Ray(Point p, Vector v){
        p0 = p;
        dir = v.normalize();
    }

    /**
     * Get value of p0
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Get value of dir
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Method to check if our ray is equals to the parameter o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Ray ray)) return false;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    /**
     * Method to print the class
     */
    @Override
    public String toString() {
        return "Ray: " +
                "p0=" + p0 +
                ", dir=" + dir;
    }
}
