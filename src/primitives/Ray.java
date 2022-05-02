package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.*;

/**
 * Ray class represents three-dimensional ray in 3D Cartesian coordinate
 * system
 *
 * @author Daniel Wolpert, Amitay Cahalon
 */
public class Ray {
    /**
     * Size of ray's starting point for shading rays
     */
    public static final double DELTA = 0.1;

    private final Point p0;
    private final Vector dir;

    /**
     * Constructor using point and vector
     *
     * @param p the starting point of the ray
     * @param v the direction vector of the ray
     */
    public Ray(Point p, Vector v) {
        p0 = p;
        dir = v.normalize();
    }

    /**
     * constructor for construction a ray with moved head in delta direction
     * @param head initial head of ray
     * @param direction direction of ray
     * @param normal normal to geometry
     */
    public Ray(Point head, Vector direction, Vector normal) {
        double nd = normal.dotProduct(direction);
        // nd can't be 0 because of intersections with objects
        Vector delta = normal.scale(nd > 0 ? DELTA : -DELTA);
        p0  = head.add(delta);
        dir = direction.normalize();
    }

    /**
     * Gets the starting point of the ray
     *
     * @return the point
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Get value of direction vector
     *
     * @return the direction
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Calculates the point where starts at p0 and scaled by t.
     *
     * @param t The scalar to scale the direction with.
     * @return The calculated point.
     */
    public Point getPoint(double t) {
        if (isZero(t)) return p0;
        return p0.add(dir.scale(t));
    }

    /**
     * Finds the point closest to the beginning of the ray
     *
     * @param points a list of points
     * @return the point
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }


    /**
     * Finds the point that is the closest to the ray's head
     *
     * @param pointList a list of tuples of a geometry and a point
     * @return the point
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> pointList) {
        if (pointList == null) return null;
        double shortestDistance = Double.POSITIVE_INFINITY;
        GeoPoint closestDistancePoint = null;
        for (GeoPoint geoPoint : pointList) {
            double distanceFromPoint = this.p0.distance(geoPoint.point);
            if (shortestDistance > distanceFromPoint) {
                shortestDistance = distanceFromPoint;
                closestDistancePoint = geoPoint;
            }
        }
        return closestDistancePoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Ray ray)) return false;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public String toString() {
        return "Ray: " +
                "p0=" + p0 +
                ", dir=" + dir;
    }
}
