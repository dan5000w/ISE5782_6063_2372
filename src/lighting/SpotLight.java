package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Spotlight class represents a directional light source with a position
 */
public class SpotLight extends PointLight {
    private Vector direction;

    /**
     * Constructor for spotlight, sets direction value and uses super constructor for position and intensity values
     *
     * @param intensity
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * Builder pattern setter
     *
     * @param direction the direction of the light
     * @return this
     */
    public SpotLight setDirection(Vector direction) {
        this.direction = direction.normalize();
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double d = p.distance(position);
        // iL = I0 * MAX(0, dir * l)/ kC + kL * d + kQ * d^2
        return getIntensity().scale(Math.max(0, direction.dotProduct(getL(p)))
                / (kC + kL * d + kQ * d * d));
    }
}
