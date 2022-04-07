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
        if (pointList == null)
            return scene.background;
        return calcColor(ray.findClosestGeoPoint(pointList), ray);
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

    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        int nShininess = material.getShininess();
        double kd = material.getKd(), ks = material.getKs();
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                double ln = l.dotProduct(n);
                color = color.add(calcDiffusive(kd, ln, lightIntensity),
                        calcSpecular(ks, l, n, ln, v, nShininess, lightIntensity));
            }
        }
        return color;
    }

    private Color calcSpecular(double ks, Vector l, Vector n, double ln, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(2 * ln)).normalize();
        return lightIntensity.scale(ks * Math.pow(-v.dotProduct(r), nShininess));
    }

    private Color calcDiffusive(double kd, double ln, Color lightIntensity) {
        return lightIntensity.scale(kd * Math.abs(ln));
    }
}
