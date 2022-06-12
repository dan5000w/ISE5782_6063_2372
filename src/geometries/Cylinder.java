package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.*;

/**
 * Cylinder class represents three-dimensional Cylinder in 3D Cartesian coordinate
 * system
 *
 * @author Daniel Wolpert, Amitay Cahalon
 */
public class Cylinder extends Tube implements Boundable {

    private final double height;

    /**
     * Constructor of cylinder using ray, radius and height
     *
     * @param axisRay ray in the middle of the cylinder
     * @param radius  radius of the cylinder
     * @param height  height of the cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }


    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    /**
     * get the height of the cylinder
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point p) {
        // we assume point is on the cylinder, so we check if it is on top, bottom or side.
        Vector rayDir = axisRay.getDir();
        Point centerBottom = axisRay.getP0();
        Point centerTop = axisRay.getPoint(height);
        if (p.equals(centerTop) || isZero(p.subtract(centerTop).dotProduct(rayDir))) { // on the top
            return rayDir;
        } else if (p.equals(centerBottom) || isZero(p.subtract(centerBottom).dotProduct(rayDir))) { //on the bottom
            return rayDir.scale(-1);
        } else { // on the side
            return super.getNormal(p);
        }
    }


    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        List<GeoPoint> intersections = super.findGeoIntersections(ray, maxDistance);

        Point p0 = axisRay.getP0();
        Vector dir = axisRay.getDir();

        if (intersections != null) {
            List<GeoPoint> temp = new LinkedList<>();

            for (GeoPoint g : intersections) {
                double pointHeight = alignZero(g.point.subtract(p0).dotProduct(dir));
                if (pointHeight > 0 && pointHeight < height) {
                    temp.add(g);
                }
            }

            if (temp.isEmpty()) {
                intersections = null;
            } else {
                intersections = temp;
            }
        }

        List<GeoPoint> planeIntersection = new Plane(p0, dir).findGeoIntersections(ray);
        if (planeIntersection != null) {
            GeoPoint point = planeIntersection.get(0);
            if (point.point.equals(p0) || alignZero(point.point.subtract(p0).lengthSquared() - radius * radius) < 0) {
                if (intersections == null) {
                    intersections = new LinkedList<>();
                }
                point.geometry = this;
                intersections.add(point);
            }
        }

        Point p1 = p0.add(dir.scale(height));

        planeIntersection = new Plane(p1, dir).findGeoIntersections(ray);
        if (planeIntersection != null) {
            GeoPoint point = planeIntersection.get(0);
            if (point.point.equals(p1) || alignZero(point.point.subtract(p1).lengthSquared() - radius * radius) < 0) {
                if (intersections == null) {
                    intersections = new LinkedList<>();
                }
                point.geometry = this;
                intersections.add(point);
            }
        }

        if (intersections != null) {
            for (GeoPoint g : intersections) {
                g.geometry = this;
            }
        }

        return intersections;
    }

    @Override
    public AxisAlignedBoundingBox getAxisAlignedBoundingBox() {
        double minX, minY, minZ, maxX, maxY, maxZ;

        Point o1 = axisRay.getP0(); // middle of first end
        Point o2 = o1.add(axisRay.getDir().scale(height));//middle of second end TODO

        // middle point of side circles plus a radius offset is a good approximation for the bounding box
        if (o1.getX() > o2.getX()) {
            maxX = o1.getX() + radius;
            minX = o2.getX() - radius;
        } else {
            maxX = o2.getX() + radius;
            minX = o1.getX() - radius;
        }

        if (o1.getY() > o2.getY()) {
            maxY = o1.getY() + radius;
            minY = o2.getY() - radius;
        } else {
            maxY = o2.getY() + radius;
            minY = o1.getY() - radius;
        }

        if (o1.getZ() > o2.getZ()) {
            maxZ = o1.getZ() + radius;
            minZ = o2.getZ() - radius;
        } else {
            maxZ = o2.getZ() + radius;
            minZ = o1.getZ() - radius;
        }

        AxisAlignedBoundingBox res = new AxisAlignedBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
        res.addToContains(this);

        return res;
    }
}

