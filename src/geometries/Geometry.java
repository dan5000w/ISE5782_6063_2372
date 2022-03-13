package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Geometry interface represents three-dimensional objects
 * system
 * @author DW, AC
 */
public interface Geometry {
    /**
     * Gets the vector that is perpendicular to the geometry starting at the point (called normal)
     * @param p a point on the geometry
     * @return the normal vector
     */
    Vector getNormal(Point p);
}
