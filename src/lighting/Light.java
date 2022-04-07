package lighting;

import primitives.Color;

/**
 * A class that represents a lights
 */
class Light {
    private Color intensity;

    /**
     * Constructor for light, sets intensity value
     *
     * @param intensity the lights intensity
     */
    public Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * The colors intensity
     *
     * @return the intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}
