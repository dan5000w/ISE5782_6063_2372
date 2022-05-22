package geometries;

import primitives.*;

import java.util.List;

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
        public Geometry geometry;
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
     * finds intersections between a shape and a ray where distance to ray head
     * is smaller than max distance
     * we've chosen a linked list implementation because of adding complexity and
     * lack of need for random access.
     *
     * @param ray         the ray to intersect with
     * @param maxDistance maximum distance to ray head for returned points.
     * @return list of intersection points with corresponding geometries
     */
    abstract List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance);

    /**
     * Finds all intersection points and its geometries between the geometry and a ray
     *
     * @param ray the ray to intersect with
     * @return list of all the intersection points with their geometries
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
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
