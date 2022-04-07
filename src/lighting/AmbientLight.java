package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * AmbientLight class represents an ambient light
 *
 * @author Daniel Wolpert, Amitay Cahalon
 */
public class AmbientLight extends Light {
    //private Color intensity;

    /**
     * Default Constructor, sets color intensity to black
     */
    public AmbientLight() {
        super(Color.BLACK);
    }

    /**
     * Calculates the power of the color intensity
     * a using light intensity and absorption coefficient
     *
     * @param iA light intensity by RGB composites
     * @param kA absorption coefficient of the light
     */
    public AmbientLight(Color iA, Double3 kA) {
        super(iA.scale(kA));
    }
}
