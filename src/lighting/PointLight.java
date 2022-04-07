package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Point light class represents a non-directional light source with a position
 */
public class PointLight extends Light implements LightSource {

    /**
     * The points position
     */
    protected Point position;

    /**
     * An attenuation coefficient of the light source
     */
    protected double kC = 1, kL = 0, kQ = 0;

    /**
     * Constructor for point-light, sets position value and uses super constructor for intensity's value
     *
     * @param intensity the point lights intensity
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;

    }

    /**
     * Builder pattern setter
     *
     * @param position the position of the Light
     * @return this
     */
    public PointLight setPosition(Point position) {
        this.position = position;
        return this;
    }

    /**
     * Builder pattern setter
     *
     * @param kC constant attenuation factor
     * @return this
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Builder pattern setter
     *
     * @param kL linear attenuation factor
     * @return this
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Builder pattern setter
     *
     * @param kQ quadratic attenuation factor
     * @return this
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double d = p.distance(position);
        // iL = I0 / kC + kL * d + kQ * d^2
        return getIntensity().scale(1 / (kC + kL * d + kQ * d * d));
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
}
