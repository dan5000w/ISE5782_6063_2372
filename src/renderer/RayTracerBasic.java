package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * RayTracerBasic Class is a class that implements the
 * ray tracers, which are calculating the color of the point.
 *
 * @author Daniel Wolpert, Amitay Cahalon
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * Size of ray's starting point for shading rays
     */
    private static final double DELTA = 0.1;

    /**
     * Constructor, sets the scene
     *
     * @param scene a scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> pointList = scene.geometries.findGeoIntersections(ray);
        return pointList == null ? scene.background //
                : calcColor(ray.findClosestGeoPoint(pointList), ray);
    }

    /**
     * calculates the color with the intensity of the scene's ambient Light
     *
     * @param geoPoint a point
     * @return the color
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(geoPoint.geometry.getEmission(),
                        calcLocalEffects(geoPoint, ray));
    }

    /**
     * calculates the color at a specific geometry point according to the Phong reflectance model
     *
     * @param intersection the geometry point
     * @param ray          the ray
     * @return the color
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        int nShininess = material.nShininess;
        Double3 kd = material.kD, ks = material.kS;
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                if (unshaded(intersection, lightSource, l, n, nv)) {
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                double ln = l.dotProduct(n);
                color = color.add(calcDiffusive(kd, ln, lightIntensity),
                        calcSpecular(ks, l, n, ln, v, nShininess, lightIntensity));
            }
            }
        }
        return color;
    }

    private Color calcSpecular(Double3 ks, Vector l, Vector n, double ln, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(2 * ln)).normalize();
        double minusVR = -v.dotProduct(r);
        return alignZero(minusVR) <= 0 ? Color.BLACK //
                : lightIntensity.scale(ks.scale(Math.pow(-v.dotProduct(r), nShininess)));
    }

    private Color calcDiffusive(Double3 kd, double ln, Color lightIntensity) {
        return lightIntensity.scale(kd.scale(Math.abs(ln)));
    }

    /**
     * Checks whether the point should have a shadow or not
     *
     * @param gp a point on a geometry
     * @param l  TODO
     * @param n  TODO
     * @return whether the point should have a shadow
     */
    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n, double nv) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(nv < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return true;
        return intersections
                .stream()
                .noneMatch(geoPoint -> point.distance(geoPoint.point) < light.getDistance(geoPoint.point));
    }
}
