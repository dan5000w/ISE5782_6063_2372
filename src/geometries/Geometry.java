package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * Geometry interface represents three-dimensional objects system
 *
 * @author Daniel Wolpert, Amitay Cahalon
 */
public abstract class Geometry extends Intersectable {

    /**
     * The emission color of the geometry
     */
    protected Color emission = Color.BLACK;

    private Material material = new Material();

    /**
     * Gets value of the emission color
     *
     * @return the emission color
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Sets value of the emission color
     *
     * @param emission the updated geometry
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Gets value of material
     *
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Setter of the material
     *
     * @param material the material type
     * @return the updated geometry
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * Gets the vector that is perpendicular to the geometry starting at the point (called normal)
     *
     * @param p a point on the geometry
     * @return the normal vector
     */
    public abstract Vector getNormal(Point p);
}
