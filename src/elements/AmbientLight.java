package elements;

import primitives.Color;
import primitives.Double3;

/**
 * AmbientLight class represents an ambient light
 *
 * @author Daniel Wolpert, Amitay Cahalon
 */
public class AmbientLight {
    private Color intensity;

    /**
     * Default Constructor, sets color intensity to black
     */
    public AmbientLight() {
        intensity = Color.BLACK;
    }

    /**
     * Calculates the power of the color intensity
     * a using light intensity and absorption coefficient
     *
     * @param iA light intensity by RGB composites
     * @param kA absorption coefficient of the light
     */
    public AmbientLight(Color iA, Double3 kA) {
        this.intensity = iA.scale(kA);
    }

    /**
     * Gets value of the color intensity
     *
     * @return the color
     */
    public Color getIntensity() {
        return this.intensity;
    }
}
