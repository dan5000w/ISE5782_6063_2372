package lighting;

import primitives.*;

/**
 * An interface for light sources
 */
public interface LightSource {
    /**
     * template for getIntensity function
     *
     * @param p the point
     * @return The intensity of the light source in the given point
     */
    Color getIntensity(Point p);

    /**
     * template for getL function
     *
     * @param p the point
     * @return The vector from the given point to the light source
     */
    Vector getL(Point p);

}
