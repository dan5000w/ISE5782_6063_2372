package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.*;

/**
 * Geometries class stores the group of geometrical shapes (or the group of groups of geometries).
 *
 * @author Daniel Wolpert, Amitay Cahalon
 */
public class Geometries implements Intersectable {
    private final List<Intersectable> intersectableList = new LinkedList<>();

    /**
     * Default constructor
     */
    public Geometries() {
    }

    /**
     * Constructor using geometries
     *
     * @param geometries a group of geometrical shapes
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * A function which adds an object to the intersectable collection
     *
     * @param geometries a group of geometrical shapes
     */
    public void add(Intersectable... geometries) {
        intersectableList.addAll(Arrays.asList(geometries));
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = null;

        for (Intersectable item : intersectableList) {
            List<Point> itemIntersectionPoints = item.findIntersections(ray);
            if (itemIntersectionPoints != null) {
                if (result == null)
                    result = new LinkedList<>();
                result.addAll(itemIntersectionPoints);
            }
        }
        return result;
    }
}
