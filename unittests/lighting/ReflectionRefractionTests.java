package lighting;

import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import renderer.ImageWriter;
import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 *
 * @author dzilb
 */
public class ReflectionRefractionTests {
    private Scene scene = new Scene("Test scene");

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 1, 0), new Vector(0, 0, -1)) //
                .setVPSize(150, 150).setVPDistance(1000);

        scene.geometries.add( //
                new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(new Double3(0.3))),
                new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setKl(0.0004).setKq(0.0000006));

        camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spotlight
     */
    @Test
    public void twoSpheresOnMirrors() {
        Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 1, 0), new Vector(0, 0, -1)) //
                .setVPSize(2500, 2500).setVPDistance(10000); //

        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

        scene.geometries.add( //
                new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 0, 100)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(new Double3(0.5))),
                new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 20, 20)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setKr(Double3.ONE)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(-1500, -1500, -2000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setKr(new Double3(0.5))));

        scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
                .setKl(0.00001).setKq(0.000005));

        ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * Produce a picture of two triangles lighted by a spotlight with a partially
     * transparent Sphere producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 1, 0), new Vector(0, 0, -1)) //
                .setVPSize(200, 200).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        scene.geometries.add( //
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(new Double3(0.6))));

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
                .setKl(4E-5).setKq(2E-7));

        ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * An image that we created, with over 10 geometries and lights, reflections and refractions.
     */
    /*
    @Test
    public void specialPicture() {
        Camera camera1 = new Camera(new Point(0, 0, 2000), new Vector(0, 1, 0), new Vector(0, 0, -1)) //
                .setVPSize(200, 200).setVPDistance(1000);

        Material material1 = new Material().setKd(0.5).setKs(0.5).setShininess(300);
        Material material2 = new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(new Double3(0.6));
        Material material3 = new Material().setKd(0.5).setKs(0.5).setShininess(60);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        scene.setBackground(new Color(150, 150, 250).reduce(1.125));

        scene.geometries.add( //
                new Triangle(new Point(100, 100, 0), new Point(100, -100, 0), new Point(0, 0, 400)) //
                        .setMaterial(material3), //
                new Triangle(new Point(100, -100, 0), new Point(-100, -100, 0), new Point(0, 0, 400)) //
                        .setMaterial(material3),
                new Triangle(new Point(-100, -100, 0), new Point(-100, 100, 0), new Point(0, 0, 400)) //
                        .setMaterial(material3), //
                new Triangle(new Point(-100, 100, 0), new Point(100, 100, 0), new Point(0, 0, 400)) //
                        .setMaterial(material3),
                new Sphere(new Point(50, 50, 600), 25).setEmission(new Color(BLUE)) //
                        .setMaterial(material1),
                new Sphere(new Point(50, -50, 500), 20).setEmission(new Color(BLUE)) //
                        .setMaterial(material1),
                new Sphere(new Point(-50, -20, 400), 15).setEmission(new Color(BLUE)) //
                        .setMaterial(material2),
                new Sphere(new Point(-50, 50, 300), 10).setEmission(new Color(BLUE)) //
                        .setMaterial(material1),
                new Triangle(new Point(2000, 2000, -600), new Point(2000, -2000, -600), new Point(0, 0, 0)) //
                        .setMaterial(material1), //
                new Triangle(new Point(2000, -2000, -600), new Point(-2000, -2000, -600), new Point(0, 0, 0)) //
                        .setMaterial(material1),
                new Triangle(new Point(-2000, -2000, -600), new Point(-2000, 2000, -600), new Point(0, 0, 0)) //
                        .setMaterial(material1), //
                new Triangle(new Point(-2000, 2000, -600), new Point(2000, 2000, -600), new Point(0, 0, 0)) //
                        .setMaterial(material1)
        );

        scene.lights.add(new SpotLight(new Color(200, 270, 200), new Point(-200, 50, 900), new Vector(20, 0, -40))
                .setKl(4E-5).setKq(2E-7));

        ImageWriter imageWriter1 = new ImageWriter("specialImage", 1000, 1000);
        camera1.setImageWriter(imageWriter1) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * An image that we created, with over 10 geometries and lights, reflections and refractions, from a different angle.
     */
    /*
    @Test
    public void specialPictureDifferentAngle() {
        Camera camera2 = new Camera(new Point(-200, 200, 700), new Vector(1, -1, 1), new Vector(1, -1, -2)) //
                .setVPSize(200, 200).setVPDistance(100);

        Material material1 = new Material().setKd(0.5).setKs(0.5).setShininess(300);
        Material material2 = new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(new Double3(0.6));
        Material material3 = new Material().setKd(0.5).setKs(0.5).setShininess(60);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        scene.setBackground(new Color(150, 150, 250).reduce(1.125));

        scene.geometries.add( //
                new Triangle(new Point(100, 100, 0), new Point(100, -100, 0), new Point(0, 0, 400)) //
                        .setMaterial(material3), //
                new Triangle(new Point(100, -100, 0), new Point(-100, -100, 0), new Point(0, 0, 400)) //
                        .setMaterial(material3),
                new Triangle(new Point(-100, -100, 0), new Point(-100, 100, 0), new Point(0, 0, 400)) //
                        .setMaterial(material3), //
                new Triangle(new Point(-100, 100, 0), new Point(100, 100, 0), new Point(0, 0, 400)) //
                        .setMaterial(material3),
                new Sphere(new Point(50, 50, 600), 25).setEmission(new Color(BLUE)) //
                        .setMaterial(material1),
                new Sphere(new Point(50, -50, 500), 20).setEmission(new Color(BLUE)) //
                        .setMaterial(material1),
                new Sphere(new Point(-50, -20, 400), 15).setEmission(new Color(BLUE)) //
                        .setMaterial(material2),
                new Sphere(new Point(-50, 50, 300), 10).setEmission(new Color(BLUE)) //
                        .setMaterial(material1),
                new Triangle(new Point(2000, 2000, -600), new Point(2000, -2000, -600), new Point(0, 0, 0)) //
                        .setMaterial(material1), //
                new Triangle(new Point(2000, -2000, -600), new Point(-2000, -2000, -600), new Point(0, 0, 0)) //
                        .setMaterial(material1),
                new Triangle(new Point(-2000, -2000, -600), new Point(-2000, 2000, -600), new Point(0, 0, 0)) //
                        .setMaterial(material1), //
                new Triangle(new Point(-2000, 2000, -600), new Point(2000, 2000, -600), new Point(0, 0, 0)) //
                        .setMaterial(material1)
        );

        scene.lights.add(new SpotLight(new Color(200, 270, 200), new Point(-200, 50, 900), new Vector(20, 0, -40))
                .setKl(4E-5).setKq(2E-7));

        ImageWriter imageWriter2 = new ImageWriter("specialImageDifferentAngle", 1000, 1000);
        camera2.setImageWriter(imageWriter2) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    } */
}
