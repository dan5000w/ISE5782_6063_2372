package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.*;

/**
 * Geometries class stores the group of geometrical shapes (or the group of groups of geometries).
 *
 * @author DW, AC
 */
public class Geometries implements Intersectable {

    private List<Intersectable> intersectableList;

    /**
     * Default constructor
     */
    public Geometries() {
        intersectableList = new LinkedList<>();
    }

    /**
     * Constructor using geometries
     *
     * @param geometries a group of geometrical shapes
     */
    public Geometries(Intersectable... geometries) {
        intersectableList = new ArrayList<>();
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
                if (result == null) {
                    result = new LinkedList<>();
                }
                result.addAll(itemIntersectionPoints);
            }
        }
        return result;
    }
}
