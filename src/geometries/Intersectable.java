package geometries;

import primitives.*;
import java.util.*;

/**
 * Basic interface for intersectable geometries.
 *
 * @author Daniel Wolpert, Amitay Cahalon
 */
public interface Intersectable {
    /**
     * Finds all intersection points between the geometry and a ray
     *
     * @param ray the ray to intersect with
     * @return list of all the intersection points
     */
    List<Point> findIntersections(Ray ray);
}
