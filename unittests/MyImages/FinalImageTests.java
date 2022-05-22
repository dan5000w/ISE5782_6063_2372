package MyImages;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;
import lighting.SpotLight;

/**
 * Test rendering a basic image
 *
 * @author Daniel Wolpert & Amitay Cahalon
 */
public class FinalImageTests {

    @Test
    public void Final() {
        Scene scene = new Scene("The test scene");
        Camera camera = new Camera(
                new Point(7, 0, 1), new Vector(0, 0, 1), new Vector(-1, 0, 0))
                .setVPSize(1, 1).setVPDistance(1).setNumOfAliasRays(5);
        scene.setBackground(new Color(0, 150, 210));
        scene.geometries.add(
                new Sphere(new Point(-1, 0, 1), 1)
                        .setEmission(new Color(0, 0, 200)).setMaterial(new Material()
                                .setKd(0.5).setKs(0.5).setShininess(40)),
                new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 0))
                        .setEmission(new Color(40, 40, 40)).setMaterial(new Material().setKd(0.5).setKs(0.5)
                                .setShininess(50).setKr(new Double3(0.95))),
                new Triangle(new Point(-1, 0, 3), new Point(1, -2, 0), new Point(-3, -2, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)),
                new Triangle(new Point(-1, 0, 3), new Point(1, 2, 0), new Point(-3, 2, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)),
                new Triangle(new Point(-1, 0, 3), new Point(-3, -2, 0), new Point(-3, 2, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)),
                new Triangle(new Point(-1, 0, 3), new Point(1, -2, 0), new Point(1, 2, 0))
                        .setMaterial(new Material()
                                .setKd(0.8).setKs(0.2).setShininess(300).setKt(new Double3(0.5))),
                new Sphere(new Point(0, -1, 0.5), 0.5)
                        .setEmission(new Color(0, 200, 0))
                        .setMaterial(new Material()
                                .setKd(0.5).setKs(0.5).setShininess(40).setKr(new Double3(0.5))),
                new Sphere(new Point(0, 1, 0.5), 0.5)
                        .setEmission(new Color(200, 0, 0))
                        .setMaterial(new Material()
                                .setKd(0.5).setKs(0.5).setShininess(40).setKr(new Double3(0.3))));
        scene.lights.add(new SpotLight(new Color(255, 255, 255),
                new Point(3, 3, 3), new Vector(-1, -0.04, 0)).setKc(1.4)
                .setLengthOfTheSide(2).setSoftShadowsRays(10));

        camera.setImageWriter(
                        new ImageWriter("Final with Anti-aliasing with soft shadows", 500, 500))
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }
}
