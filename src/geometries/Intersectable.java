package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * basic interface for intersectable geometries.
 *
 * @author DW, AC
 */
public interface Intersectable {
    /**
     * Finds all intersection points
     *
     * @param ray the ray
     * @return all intersection points with the ray
     */
    List<Point> findIntersections(Ray ray);
}
