package lighting;

import primitives.*;

import static primitives.Util.alignZero;

/**
 * Spotlight class represents a directional light source with a position
 */
public class SpotLight extends PointLight {
    private final Vector direction;

    /**
     * Constructor for spotlight, sets direction value and uses super constructor for position and intensity values
     *
     * @param intensity the color's intensity
     * @param position the position of the camera
     * @param direction the lights direction
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        double d = p.distance(position);
        double dl = direction.dotProduct(getL(p));
        if (alignZero(dl) <= 0) return Color.BLACK;
        // iL = I0 * MAX(0, dir * l)/ kC + kL * d + kQ * d^2
        return super.getIntensity(p).scale(dl);
    }
}
