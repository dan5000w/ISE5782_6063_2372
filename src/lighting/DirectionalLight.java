package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Directional light class represents a light source with a direction
 */
public class DirectionalLight extends Light implements LightSource {

    private Vector direction;

    /**
     * Builder pattern setter
     *
     * @param intensity the colors intensity
     */
    public DirectionalLight(Color intensity, Vector dir) {
        super(intensity);
        direction = dir;
    }

    /**
     * Builder pattern setter
     *
     * @param direction the direction of the light
     * @return this
     */
    public DirectionalLight setDirection(Vector direction) {
        this.direction = direction;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction.normalize();
    }
}
