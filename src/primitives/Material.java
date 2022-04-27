package primitives;

/**
 * Material class is a PDS representing a geometry's attenuation and shininess constants
 */
public class Material {
    /**
     * The coefficient of the specular light
     */
    public Double3 kS = Double3.ZERO;

    /**
     * The attenuation coefficient of the diffusive light
     */
    public Double3 kD = Double3.ZERO;

    /**
     * The shininess level of the material
     */
    public int nShininess;


    /**
     * Setter of the specular light's coefficient kS
     *
     * @param kS the specular light's coefficient
     * @return the updated material
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Setter of the specular light's coefficient kS
     *
     * @param kS the specular light's coefficient
     * @return the updated material
     */
    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Setter of the specular light's coefficient kS
     *
     * @param kD the specular light's attenuation coefficient kD
     * @return the updated material
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Setter of the specular light's coefficient kS
     *
     * @param kD the specular light's attenuation coefficient kD
     * @return the updated material
     */
    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * Setter of the nShininess
     *
     * @param nShininess The material's level of shininess
     * @return the updated material
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
