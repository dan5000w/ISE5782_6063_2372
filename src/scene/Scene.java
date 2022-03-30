package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;

/**
 * Scene class represents a scene in 3D cartesian coordinate system
 *
 * @author Daniel Wolpert, Amitay Cahalon
 */
public class Scene {
    /**
     * The name of the scene
     */
    public String name;

    /**
     * The background color of the scene
     */
    public Color background = Color.BLACK;

    /**
     * The ambient color of the scene
     */
    public AmbientLight ambientLight = new AmbientLight();

    /**
     * A group of all geometries in the scene
     */
    public Geometries geometries = new Geometries();

    /**
     * Constructor, sets the name of the scene
     *
     * @param name the name of the scene
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * Setter of the background color
     *
     * @param background the background color of the scene
     * @return the updated scene
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Setter of the scene's ambient light
     *
     * @param ambientLight the ambient color of the scene
     * @return the updated scene
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Setter of the geometries in the scene
     *
     * @param geometries a group of all geometries in the scene
     * @return the updated scene
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
