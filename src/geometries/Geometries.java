//package geometries;
//
//import primitives.Ray;
//
//import java.util.*;
//
///**
// * Geometries class stores the group of geometrical shapes (or the group of groups of geometries).
// *
// * @author Daniel Wolpert, Amitay Cahalon
// */
//public class Geometries extends Intersectable {
//    private final List<Intersectable> intersectableList = new LinkedList<>();
//
//    /**
//     * Default constructor
//     */
//    public Geometries() {
//    }
//
//    /**
//     * Constructor using geometries
//     *
//     * @param geometries a group of geometrical shapes
//     */
//    public Geometries(Intersectable... geometries) {
//        add(geometries);
//    }
//
//    /**
//     * A function which adds an object to the intersectable collection
//     *
//     * @param geometries a group of geometrical shapes
//     */
//    public void add(Intersectable... geometries) {
//        intersectableList.addAll(Arrays.asList(geometries));
//    }
//
//    @Override
//    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
//        LinkedList<GeoPoint> intersections = null;
//        for (Intersectable g : intersectableList) {
//            List<GeoPoint> temp = g.findGeoIntersections(ray, maxDistance);
//            if (temp != null) {
//                if (intersections == null)
//                    intersections = new LinkedList<>(temp);
//                else
//                    intersections.addAll(temp);
//            }
//        }
//        return intersections;
//    }
//
//}

package geometries;

import primitives.Ray;

import java.util.*;

/**
 * Geometries class implements a list of geometries
 */
public class Geometries extends Intersectable {

    /**
     * A list of geometries. (According to the composite design pattern)
     */
    private List<Intersectable> geometries;

    /**
     * If true, then the geometries class will use AABB in the calculations, and vice versa.
     */
    private boolean axisAlignedBoundingBox = false;

    /**
     * Constructs a new instance with empty list of geometries.
     */
    public Geometries() {
        this.geometries = new LinkedList<>(); // I used array list because of the constant access time.
    }

    /**
     * Constructs a new instance with a collections of geometries.
     *
     * @param geometries The geometries to insert to the new instance.
     */
    public Geometries(Intersectable... geometries) {
        if (axisAlignedBoundingBox) {
            this.geometries = List.of(geometries);

            // create a list of all the geometries in the scene
            ArrayList<Intersectable> geos = new ArrayList<>(List.of(geometries));

            // a list of all the boundable geometries in the scene
            List<Boundable> boundables = new LinkedList<>();

            // move all the boundables from geos to boundables list
            for (Intersectable g : geometries) {
                if (g instanceof Boundable) {
                    geos.remove(g);
                    boundables.add((Boundable) g);
                }
            }

            // create a aabb tree for the boundable geometries and add the tree to the geometry list
            geos.add(AxisAlignedBoundingBox.createTree(boundables));

            this.geometries = geos;
        } else
            this.geometries = List.of(geometries);
    }

    /**
     * Add new geometries into the list
     *
     * @param geometries the new geometries to add
     */
    public void add(Intersectable... geometries) {
        if (axisAlignedBoundingBox) {
            //create a list of all the geometries already existing in the scene
            List<Intersectable> geos = new ArrayList<>();

            //add all the un-boundable ones to the ones that are bounded in boxes
            for (Intersectable item : this.geometries) {
                if (item instanceof Geometry) {
                    geos.add(item);
                } else {
                    geos.addAll(((AxisAlignedBoundingBox) item).getAllGeometries());
                }
            }

            // Add all new geometries to the existing ones
            geos.addAll(Arrays.asList(geometries));

            //a list of all the boundable geometries in the scene
            List<Boundable> boundables = new ArrayList<>();

            //move all the boundables from geos to boundables list
            for (Intersectable g : geometries) {
                if (g instanceof Boundable) {
                    geos.remove(g);
                    boundables.add((Boundable) g);
                }
            }
            // create a aabb tree for the boundable geometries and add the tree to the geometry list
            AxisAlignedBoundingBox aabb = AxisAlignedBoundingBox.createTree(boundables);
            if (aabb != null)
                geos.add(aabb);

            this.geometries = geos;
        } else
            this.geometries.addAll(Arrays.asList(geometries));
    }


    /**
     * Finds all the intersection points with geometries in our list
     *
     * @param ray The ray to check intersection points with.
     * @return List of the geometric intersection points
     */
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        ArrayList<GeoPoint> lst = new ArrayList<>();
        for (Intersectable geometry : geometries) {
            var points = geometry.findGeoIntersections(ray, maxDistance);
            if (points != null) {
                lst.addAll(points);
            }
        }
        if (lst.size() == 0) return null;
        return lst;
    }

    /**
     * @return The list of geometries.
     */
    public List<Intersectable> getGeometries() {
        return geometries;
    }

    /**
     * @param axisAlignedBoundingBox The boolean value to set if use AABB.
     * @return The current instance (Builder pattern).
     */
    public Geometries setAxisAlignedBoundingBox(boolean axisAlignedBoundingBox) {
        this.axisAlignedBoundingBox = axisAlignedBoundingBox;
        return this;
    }
}
