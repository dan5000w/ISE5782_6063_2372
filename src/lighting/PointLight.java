package lighting;

import primitives.*;

/**
 * Point light class represents a non-directional light source with a position
 */
public class PointLight extends Light implements LightSource {

    /**
     * The points position
     */
    protected final Point position;

    /**
     * An attenuation coefficient of the light source
     */
    protected double kC = 1;

    /**
     * An attenuation coefficient of the light source
     */
    protected double kL = 0;

    /**
     * An attenuation coefficient of the light source
     */
    protected double kQ = 0;

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
     * Setter of the light source's attenuation coefficient kC
     *
     * @param kC constant attenuation factor
     * @return the updated point light
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Setter of the light source's attenuation coefficient kL
     *
     * @param kL constant attenuation factor
     * @return the updated point light
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Setter of the light source's attenuation coefficient kQ
     *
     * @param kQ constant attenuation factor
     * @return the updated point light
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double d = p.distance(position);
        // iL = I0 / kC + kL * d + kQ * d^2
        return intensity.scale(1 / (kC + kL * d + kQ * d * d));
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point p) {
        return p.distance(position);
    }
}
