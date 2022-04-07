package primitives;

/**
 * Material class is a PDS representing a geometry's attenuation and shininess constants
 */
public class Material {
    /**
     * The coefficient of the specular light
     */
    public double kS = 0;

    /**
     * The attenuation coefficient of the diffusive light
     */
    public double kD = 0;

    /**
     * The shininess level of the material
     */
    public int nShininess;


    /**
     * Builder pattern setter
     *
     * @param kS The coefficient of the specular light
     */
    public Material setKs(double kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Builder pattern setter
     *
     * @param kD The attenuation coefficient of the diffusive light
     */
    public Material setKd(double kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Builder pattern setter
     *
     * @param nShininess The shininess level
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * Gets value of kS
     *
     * @return kS
     */
    public double getKs() {
        return kS;
    }

    /**
     * Gets value of kS
     *
     * @return kD
     */
    public double getKd() {
        return kD;
    }

    /**
     * Gets value of the materials Shininess
     *
     * @return the shininess
     */
    public int getShininess() {
        return nShininess;
    }
}
