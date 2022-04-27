package lighting;

import primitives.Color;

/**
 * A class that represents a lights
 */
class Light {
    protected final Color intensity;

    /**
     * Constructor for light, sets intensity value
     *
     * @param intensity the lights intensity
     */
    public Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Gets the colors intensity
     *
     * @return the intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}
