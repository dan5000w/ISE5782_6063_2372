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

    private static final Double3 INITIAL_K = new Double3(1.0);
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;


    /**
     * Constructor, sets the scene
     *
     * @param scene a scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * construct the refracted ray from geometry
     *
     * @param point geometry's point
     * @param v     initial intersection ray direction
     * @param n     normal to geometry at point
     * @return refracted ray
     */
    private Ray constructRefractedRay(Point point, Vector v, Vector n) {
        return new Ray(point, v, n);
    }

    /**
     * construct the reflected ray from geometry
     *
     * @param point geometry's point
     * @param v     initial intersection ray direction
     * @param n     normal to geometry at point
     * @return reflected ray
     */
    private Ray constructReflectedRay(Point point, Vector v, Vector n) {
        Vector r = v.subtract(n.scale(2 * v.dotProduct(n))).normalize();
        return new Ray(point, r, n);
    }

    /**
     * @param ray ray to intersect scene
     * @return closest intersection on ray to ray head
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if(intersections == null) return null;
        return ray.findClosestGeoPoint(intersections);
    }


    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    /**
     * calculate the color of a given point in the scene
     *
     * @param gp  point on shape
     * @param ray initial intersecting ray
     * @return color at p.
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * calculate global and local effects using recursion
     *
     * @param intersection intersection point + geometry
     * @param ray          initial intersecting ray
     * @param level        level of recursion
     * @param k            attenuation factor
     * @return the color
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getDir(), level, k));
    }

    /**
     * calculate global effects on point (refraction and reflection)
     *
     * @param gp    point of intersection
     * @param v     initial intersecting ray
     * @param level level of recursion
     * @param k     attenuation factor
     * @return global effects additive
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        Double3 kkr = material.kR.product(k);
        if (kkr.greaterThan(MIN_CALC_COLOR_K))
            color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.kR, kkr);
        Double3 kkt = material.kT.product(k);
        if (kkt.greaterThan(MIN_CALC_COLOR_K))
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.kT, kkt));
        return color;
    }

    /**
     * calculate specific affect each time - refraction or reflection.
     *
     * @param ray   constructed reflected/refracted ray
     * @param level level of recursion
     * @param kx    kR / kT
     * @param kkx   kkR/ kkT
     * @return specific global effect
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
    }

    /**
     * calculate local effects of lights in scene
     * diffusive and specular
     *
     * @param intersection point of intersection
     * @param ray          initial intersecting ray
     * @return local effects on point in scene
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, Double3 k) {
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
            double ln = alignZero(n.dotProduct(l));
            if (ln * nv > 0) { // sign(nl) == sing(nv)
                Double3 ktr = transparency(intersection, lightSource, l, n);
                if (ktr.product(k).greaterThan(MIN_CALC_COLOR_K)) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
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
     * check if a point in scene is not shaded from a specific light source
     * @param lightSource light source
     * @param l vector from light source to point
     * @param n normal to geometry at point
     * @param geoPoint geometry and point
     * @return is not shaded
     */
    private boolean unshaded(LightSource lightSource,Vector l, Vector n, GeoPoint geoPoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection,n);
        List<GeoPoint> intersections = scene.geometries
                .findGeoIntersections(lightRay, lightSource.getDistance(geoPoint.point));

        if(intersections == null) return true;

        for (GeoPoint gp : intersections) {
            if(gp.geometry.getMaterial().kT == Double3.ZERO)
                return false;
        }
        return true;
    }


    private Double3 transparency(GeoPoint geoPoint, LightSource ls, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
        double lightDistance = ls.getDistance(geoPoint.point);
        var intersections = scene.geometries
                .findGeoIntersections(lightRay, lightDistance);
        if (intersections == null) return Double3.ONE;

        Double3 ktr = Double3.ONE;
        for (GeoPoint gp : intersections) {
            ktr = ktr.product(gp.geometry.getMaterial().kT);
            if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
        }
        return ktr;
    }
}
