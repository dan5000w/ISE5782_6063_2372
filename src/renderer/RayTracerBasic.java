package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

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
        List<Point> pointList = scene.geometries.findIntersections(ray);
        if (pointList == null)
            return scene.background;
        return calcColor(ray.findClosestPoint(pointList));
    }

    /**
     * calculates the color with the intensity of the scene's ambient Light
     *
     * @param p a point
     * @return the color
     */
    private Color calcColor(Point p) {
        return scene.ambientLight.getIntensity();
    }
}
