package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * RayTracerBase Class is an abstract class as a template to
 * ray tracers, which are calculating the color of the point.
 *
 * @author Daniel Wolpert, Amitay Cahalon
 */
abstract class RayTracerBase {
    /**
     * a scene in 3D cartesian coordinate system
     */
    protected Scene scene;

    /**
     * Constructor, sets the scene
     *
     * @param scene a scene
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Finds the intersection and the scene’s geometries
     * If there is no intersection, return the background color
     * Find the closest intersection point
     * Find the color of the intersection point (Ambient light)
     *
     * @param ray a ray
     * @return the color
     */
    public abstract Color traceRay(Ray ray);

}
