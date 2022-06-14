
package geometries;

import primitives.Ray;

import java.util.*;

/**
 * Geometries class stores the group of geometrical shapes (or the group of groups of geometries).
 *
 * @author Daniel Wolpert, Amitay Cahalon
 */
public class Geometries extends Intersectable {

    /**
     * A list of geometries
     */
    private List<Intersectable> geometries;

    /**
     * If true, then the geometries class will use axis aligned bounding box in the calculations, and vice versa.
     */
    private boolean axisAlignedBoundingBox = false;

    /**
     * Constructs a new instance with empty list of geometries.
     */
    public Geometries() {
        this.geometries = new LinkedList<>(); // I used array list because of the constant access time.
    }

    /**
     * Set whether using VBH or not
     *
     * @param axisAlignedBoundingBox The boolean value to set if using axis aligned bounding box
     * @return The current instance (Builder pattern)
     */
    public Geometries setAxisAlignedBoundingBox(boolean axisAlignedBoundingBox) {
        this.axisAlignedBoundingBox = axisAlignedBoundingBox;
        return this;
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

            // create an axis aligned bounding box tree for the boundable geometries and add the tree to the geometry list
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
                if (item instanceof Geometry)
                    geos.add(item);
                else
                    geos.addAll(((AxisAlignedBoundingBox) item).getAllGeometries());

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
            // create an axis aligned bounding box tree for the boundable geometries and add the tree to the geometry list
            AxisAlignedBoundingBox axisAlignedBoundingBox = AxisAlignedBoundingBox.createTree(boundables);
            if (axisAlignedBoundingBox != null)
                geos.add(axisAlignedBoundingBox);
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

        LinkedList<GeoPoint> intersections = null;
        for (Intersectable g : geometries) {
            List<GeoPoint> temp = g.findGeoIntersections(ray, maxDistance);
            if (temp != null) {
                if (intersections == null)
                    intersections = new LinkedList<>(temp);
                else
                    intersections.addAll(temp);
            }
        }
        return intersections;
    }

    /**
     * Gets the list of geometries
     *
     * @return The list
     */
    public List<Intersectable> getGeometries() {
        return geometries;
    }

}
