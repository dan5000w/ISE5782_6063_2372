package lighting;

import primitives.*;

/**
 * Directional light class represents a light source with a direction
 */
public class DirectionalLight extends Light implements LightSource {

    private final Vector direction;

    /**
     * Constructor of DirectionalLight
     *
     * @param intensity the colors intensity
     */
    public DirectionalLight(Color intensity, Vector dir) {
        super(intensity);
        direction = dir.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }

    @Override
    public double getDistance(Point p) {
        return Double.POSITIVE_INFINITY;
    }
}
