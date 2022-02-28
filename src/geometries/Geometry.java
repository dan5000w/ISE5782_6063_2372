package geometries;

import primitives.Point;
import primitives.Vector;

public interface Geometry {
    /**
     * Gets the vector that is perpendicular to the geometry starting at the point
     * @param p a point on the geometry
     * @return the vector that is perpendicular to the geometry starting at the point
     */
    public Vector getNormal(Point p);
}
