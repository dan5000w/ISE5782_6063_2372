package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.*;

/**
 * Geometries class stores the group of geometrical shapes (or the group of groups of geometries).
 *
 * @author Daniel Wolpert, Amitay Cahalon
 */
public class Geometries extends Intersectable {
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
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        LinkedList<GeoPoint> intersections = null;
        for (Intersectable g : intersectableList) {
            List<GeoPoint> temp = g.findGeoIntersections(ray);
            if (temp != null) {
                if (intersections == null)
                    intersections = new LinkedList<>(temp);
                else
                    intersections.addAll(temp);
            }
        }
        return intersections;
    }
}
