package geometries;

import primitives.*;

import java.util.*;

/**
 * Basic interface for intersectable geometries.
 *
 * @author Daniel Wolpert, Amitay Cahalon
 */
public abstract class Intersectable {


    /**
     * A tuple of a geometry and a point
     */
    public static class GeoPoint {
        /**
         * A geometry (plane, tube ect...)
         */
        public final Geometry geometry;
        /**
         * A point on the geometry
         */
        public final Point point;

        /**
         * Constructor of GeoPoint using geometry and point
         *
         * @param geometry a geometry
         * @param point    a point on the geometry
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry == geoPoint.geometry && point.equals(geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint:" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '!';
        }
    }

    /**
     * Adds an affiliation of a point to a geometry
     *
     * @param ray the ray to intersect with
     * @return list of all the intersection points with their geometries
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    /**
     * Finds all intersection points and its geometries between the geometry and a ray
     *
     * @param ray the ray to intersect with
     * @return list of all the intersection points with their geometries
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * Finds all intersection points between the geometry and a ray
     *
     * @param ray the ray to intersect with
     * @return list of all the intersection points
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }
}
