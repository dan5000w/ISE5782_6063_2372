package MyImages;

import lighting.SpotLight;
import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Test rendering a basic image
 *
 * @author Daniel Wolpert & Amitay Cahalon
 */
public class PokerTests {
    //@Test
    public void poker1() {
        Scene scene = new Scene("Poker with Anti-aliasing without soft shadows");

        Camera camera = new Camera(
                new Point(7, 5, 2), new Vector(0, 0, 1), new Vector(-1, -0.5, 0))
                .setVPSize(1, 1).setVPDistance(1).setNumOfAliasRays(15);
        scene.setBackground(new Color(1, 50, 32));

        scene.geometries.setAxisAlignedBoundingBox(true).add( //
                new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 0))
                        .setEmission(new Color(40, 40, 40)).setMaterial(new Material().setKd(0.5).setKs(0.5)
                                .setShininess(50).setKr(new Double3(0.95)))
                , new Triangle(Point.ZERO, new Point(0, 0, 1), new Point(1, 0, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(Point.ZERO, new Point(0, 0, 1), new Point(0, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(1, 0, 0), new Point(0, 0, 1), new Point(1, 0, 1))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(0, 1, 0), new Point(0, 0, 1), new Point(0, 1, 1))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(0, 1, 0), new Point(0, 1, 1), new Point(1, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(0, 1, 1), new Point(1, 1, 1), new Point(1, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(1, 0, 1), new Point(1, 0, 0), new Point(1, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(1, 0, 1), new Point(1, 1, 1), new Point(1, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(1, 0, 1), new Point(0, 1, 1), new Point(0, 0, 1))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))),
                new Triangle(new Point(1, 0, 1), new Point(0, 1, 1), new Point(1, 1, 1))
                        .setEmission(new Color(110, 110, 100)).setMaterial(new Material()
                                .setKd(0.8).setKs(0.2).setShininess(300).setKr(new Double3(0.1))),

                new Cylinder(new Ray(new Point(0.5, 1, 0.5), new Vector(0, 1, 0),
                        new Vector(0, 0, 1)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(1, 0.8, 0.8), new Vector(1, 0, 0),
                        new Vector(0, 1, 0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(1, 0.2, 0.2), new Vector(1, 0, 0),
                        new Vector(0, 1, 0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(0.5, 0.5, 1), new Vector(0, 0, 1),
                        new Vector(0, 1, 0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(0.8, 0.8, 1), new Vector(0, 0, 1),
                        new Vector(0, 1, 0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(0.2, 0.2, 1), new Vector(0, 0, 1),
                        new Vector(0, 1, 0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(0, 2.4, 0), new Vector(0, 0, 1),
                        new Vector(1, 0, 0)), 1, 0.2)
                        .setEmission(new Color(100, 10, 10)).setMaterial(new Material()
                                .setKd(0.2).setKs(0.1).setShininess(1).setKr(new Double3(0.2))),
                new Cylinder(new Ray(new Point(0, 2.8, 0.2), new Vector(0, 0, 1),
                        new Vector(1, 0, 0)), 1, 0.2)
                        .setEmission(new Color(0, 10, 100)).setMaterial(new Material()
                                .setKd(0.2).setKs(0.1).setShininess(1).setKr(new Double3(0.2))),
                new Cylinder(new Ray(new Point(0, 2.2, 0.4), new Vector(0, 0, 1),
                        new Vector(1, 0, 0)), 1, 0.2)
                        .setEmission(new Color(100, 10, 10)).setMaterial(new Material()
                                .setKd(0.2).setKs(0.1).setShininess(1).setKr(new Double3(0.2))),
                new Cylinder(new Ray(new Point(0, 2.7, 0.6), new Vector(0, 0, 1),
                        new Vector(1, 0, 0)), 1, 0.2)
                        .setEmission(new Color(0, 10, 100)).setMaterial(new Material()
                                .setKd(0.2).setKs(0.1).setShininess(1).setKr(new Double3(0.2))),
                new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 0))
                        .setEmission(new Color(40, 40, 40)).setMaterial(new Material().setKd(0.5).setKs(0.5)
                                .setShininess(10).setKr(new Double3(0.4))));

        scene.lights.add(new SpotLight(new Color(255, 255, 255), new Point(3, 3, 3),
                new Vector(-1, -0.25, 0)));

        camera.setImageWriter(
                        new ImageWriter("Poker with Anti-aliasing without soft shadows", 500, 500))
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    @Test
    public void poker2() {
        Scene scene = new Scene("Poker without Anti-aliasing with soft shadows");
        Camera camera = new Camera(
                new Point(7, 5, 2), new Vector(0, 0, 1), new Vector(-1, -0.5, 0))
                .setVPSize(1, 1).setVPDistance(1);
        scene.setBackground(new Color(1, 50, 32));

        scene.geometries.setAxisAlignedBoundingBox(true).add( //
                new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 0))
                        .setEmission(new Color(40, 40, 40)).setMaterial(new Material().setKd(0.5).setKs(0.5)
                                .setShininess(50).setKr(new Double3(0.95)))
                , new Triangle(Point.ZERO, new Point(0, 0, 1), new Point(1, 0, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(Point.ZERO, new Point(0, 0, 1), new Point(0, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(1, 0, 0), new Point(0, 0, 1), new Point(1, 0, 1))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(0, 1, 0), new Point(0, 0, 1), new Point(0, 1, 1))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(0, 1, 0), new Point(0, 1, 1), new Point(1, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(0, 1, 1), new Point(1, 1, 1), new Point(1, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(1, 0, 1), new Point(1, 0, 0), new Point(1, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(1, 0, 1), new Point(1, 1, 1), new Point(1, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(1, 0, 1), new Point(0, 1, 1), new Point(0, 0, 1))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))),
                new Triangle(new Point(1, 0, 1), new Point(0, 1, 1), new Point(1, 1, 1))
                        .setEmission(new Color(110, 110, 100)).setMaterial(new Material()
                                .setKd(0.8).setKs(0.2).setShininess(300).setKr(new Double3(0.1))),

                new Cylinder(new Ray(new Point(0.5, 1, 0.5), new Vector(0, 1, 0),
                        new Vector(0, 0, 1)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(1, 0.8, 0.8), new Vector(1, 0, 0),
                        new Vector(0, 1, 0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(1, 0.2, 0.2), new Vector(1, 0, 0),
                        new Vector(0, 1, 0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(0.5, 0.5, 1), new Vector(0, 0, 1),
                        new Vector(0, 1, 0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(0.8, 0.8, 1), new Vector(0, 0, 1),
                        new Vector(0, 1, 0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(0.2, 0.2, 1), new Vector(0, 0, 1),
                        new Vector(0, 1, 0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(0, 2.4, 0), new Vector(0, 0, 1),
                        new Vector(1, 0, 0)), 1, 0.2)
                        .setEmission(new Color(100, 10, 10)).setMaterial(new Material()
                                .setKd(0.2).setKs(0.1).setShininess(1).setKr(new Double3(0.2))),
                new Cylinder(new Ray(new Point(0, 2.8, 0.2), new Vector(0, 0, 1),
                        new Vector(1, 0, 0)), 1, 0.2)
                        .setEmission(new Color(0, 10, 100)).setMaterial(new Material()
                                .setKd(0.2).setKs(0.1).setShininess(1).setKr(new Double3(0.2))),
                new Cylinder(new Ray(new Point(0, 2.2, 0.4), new Vector(0, 0, 1),
                        new Vector(1, 0, 0)), 1, 0.2)
                        .setEmission(new Color(100, 10, 10)).setMaterial(new Material()
                                .setKd(0.2).setKs(0.1).setShininess(1).setKr(new Double3(0.2))),
                new Cylinder(new Ray(new Point(0, 2.7, 0.6), new Vector(0, 0, 1),
                        new Vector(1, 0, 0)), 1, 0.2)
                        .setEmission(new Color(0, 10, 100)).setMaterial(new Material()
                                .setKd(0.2).setKs(0.1).setShininess(1).setKr(new Double3(0.2))),
                new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 0))
                        .setEmission(new Color(40, 40, 40)).setMaterial(new Material().setKd(0.5).setKs(0.5)
                                .setShininess(10).setKr(new Double3(0.4))));

        scene.lights.add(new SpotLight(new Color(255, 255, 255), new Point(3, 3, 3),
                new Vector(-1, -0.25, 0)));


        scene.lights.add(new SpotLight(new Color(255, 255, 255),
                new Point(3, 3, 3), new Vector(-1, -0.25, 0))
                .setLengthOfTheSide(5).setSoftShadowsRays(100));

        camera.setImageWriter(new ImageWriter("Poker without Anti-aliasing with soft shadows", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    @Test
    public void poker3() {
        Scene scene = new Scene("Poker without Anti-aliasing without soft shadows");
        Camera camera = new Camera(
                new Point(7, 5, 2), new Vector(0, 0, 1), new Vector(-1, -0.5, 0))
                .setVPSize(1, 1).setVPDistance(1);
        scene.setBackground(new Color(1, 50, 32));

        scene.geometries.setAxisAlignedBoundingBox(true).add( //
                new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 0))
                        .setEmission(new Color(40, 40, 40)).setMaterial(new Material().setKd(0.5).setKs(0.5)
                                .setShininess(50).setKr(new Double3(0.95)))
                , new Triangle(Point.ZERO, new Point(0, 0, 1), new Point(1, 0, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(Point.ZERO, new Point(0, 0, 1), new Point(0, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(1, 0, 0), new Point(0, 0, 1), new Point(1, 0, 1))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(0, 1, 0), new Point(0, 0, 1), new Point(0, 1, 1))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(0, 1, 0), new Point(0, 1, 1), new Point(1, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(0, 1, 1), new Point(1, 1, 1), new Point(1, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(1, 0, 1), new Point(1, 0, 0), new Point(1, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(1, 0, 1), new Point(1, 1, 1), new Point(1, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(1, 0, 1), new Point(0, 1, 1), new Point(0, 0, 1))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))),
                new Triangle(new Point(1, 0, 1), new Point(0, 1, 1), new Point(1, 1, 1))
                        .setEmission(new Color(110, 110, 100)).setMaterial(new Material()
                                .setKd(0.8).setKs(0.2).setShininess(300).setKr(new Double3(0.1))),

                new Cylinder(new Ray(new Point(0.5, 1, 0.5), new Vector(0, 1, 0),
                        new Vector(0, 0, 1)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(1, 0.8, 0.8), new Vector(1, 0, 0),
                        new Vector(0, 1, 0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(1, 0.2, 0.2), new Vector(1, 0, 0),
                        new Vector(0, 1, 0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(0.5, 0.5, 1), new Vector(0, 0, 1),
                        new Vector(0, 1, 0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(0.8, 0.8, 1), new Vector(0, 0, 1),
                        new Vector(0, 1, 0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(0.2, 0.2, 1), new Vector(0, 0, 1),
                        new Vector(0, 1, 0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(0, 2.4, 0), new Vector(0, 0, 1),
                        new Vector(1, 0, 0)), 1, 0.2)
                        .setEmission(new Color(100, 10, 10)).setMaterial(new Material()
                                .setKd(0.2).setKs(0.1).setShininess(1).setKr(new Double3(0.2))),
                new Cylinder(new Ray(new Point(0, 2.8, 0.2), new Vector(0, 0, 1),
                        new Vector(1, 0, 0)), 1, 0.2)
                        .setEmission(new Color(0, 10, 100)).setMaterial(new Material()
                                .setKd(0.2).setKs(0.1).setShininess(1).setKr(new Double3(0.2))),
                new Cylinder(new Ray(new Point(0, 2.2, 0.4), new Vector(0, 0, 1),
                        new Vector(1, 0, 0)), 1, 0.2)
                        .setEmission(new Color(100, 10, 10)).setMaterial(new Material()
                                .setKd(0.2).setKs(0.1).setShininess(1).setKr(new Double3(0.2))),
                new Cylinder(new Ray(new Point(0, 2.7, 0.6), new Vector(0, 0, 1),
                        new Vector(1, 0, 0)), 1, 0.2)
                        .setEmission(new Color(0, 10, 100)).setMaterial(new Material()
                                .setKd(0.2).setKs(0.1).setShininess(1).setKr(new Double3(0.2))),
                new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 0))
                        .setEmission(new Color(40, 40, 40)).setMaterial(new Material().setKd(0.5).setKs(0.5)
                                .setShininess(10).setKr(new Double3(0.4))));

        scene.lights.add(new SpotLight(new Color(255, 255, 255), new Point(3, 3, 3),
                new Vector(-1, -0.25, 0)));

        camera.setImageWriter(new ImageWriter("Poker without Anti-aliasing without soft shadows", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    //@Test
    public void poker4() {
        Scene scene = new Scene("Poker with Anti-aliasing with soft shadows");
        Camera camera = new Camera(
                new Point(7, 5, 2), new Vector(0, 0, 1), new Vector(-1, -0.5, 0))
                .setVPSize(1, 1).setVPDistance(1).setNumOfAliasRays(5);
        scene.setBackground(new Color(1, 50, 32));

        scene.geometries.setAxisAlignedBoundingBox(true).add( //
                new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 0))
                        .setEmission(new Color(40, 40, 40)).setMaterial(new Material().setKd(0.5).setKs(0.5)
                                .setShininess(50).setKr(new Double3(0.95)))
                , new Triangle(Point.ZERO, new Point(0, 0, 1), new Point(1, 0, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(Point.ZERO, new Point(0, 0, 1), new Point(0, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(1, 0, 0), new Point(0, 0, 1), new Point(1, 0, 1))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(0, 1, 0), new Point(0, 0, 1), new Point(0, 1, 1))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(0, 1, 0), new Point(0, 1, 1), new Point(1, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(0, 1, 1), new Point(1, 1, 1), new Point(1, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(1, 0, 1), new Point(1, 0, 0), new Point(1, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(1, 0, 1), new Point(1, 1, 1), new Point(1, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(1, 0, 1), new Point(0, 1, 1), new Point(0, 0, 1))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)
                                .setKr(new Double3(0.1))),
                new Triangle(new Point(1, 0, 1), new Point(0, 1, 1), new Point(1, 1, 1))
                        .setEmission(new Color(110, 110, 100)).setMaterial(new Material()
                                .setKd(0.8).setKs(0.2).setShininess(300).setKr(new Double3(0.1))),

                new Cylinder(new Ray(new Point(0.5, 1, 0.5), new Vector(0, 1, 0),
                        new Vector(0, 0, 1)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(1, 0.8, 0.8), new Vector(1, 0, 0),
                        new Vector(0, 1, 0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(1, 0.2, 0.2), new Vector(1, 0, 0),
                        new Vector(0, 1, 0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(0.5, 0.5, 1), new Vector(0, 0, 1),
                        new Vector(0, 1, 0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(0.8, 0.8, 1), new Vector(0, 0, 1),
                        new Vector(0, 1, 0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(0.2, 0.2, 1), new Vector(0, 0, 1),
                        new Vector(0, 1, 0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(0, 2.4, 0), new Vector(0, 0, 1),
                        new Vector(1, 0, 0)), 1, 0.2)
                        .setEmission(new Color(100, 10, 10)).setMaterial(new Material()
                                .setKd(0.2).setKs(0.1).setShininess(1).setKr(new Double3(0.2))),
                new Cylinder(new Ray(new Point(0, 2.8, 0.2), new Vector(0, 0, 1),
                        new Vector(1, 0, 0)), 1, 0.2)
                        .setEmission(new Color(0, 10, 100)).setMaterial(new Material()
                                .setKd(0.2).setKs(0.1).setShininess(1).setKr(new Double3(0.2))),
                new Cylinder(new Ray(new Point(0, 2.2, 0.4), new Vector(0, 0, 1),
                        new Vector(1, 0, 0)), 1, 0.2)
                        .setEmission(new Color(100, 10, 10)).setMaterial(new Material()
                                .setKd(0.2).setKs(0.1).setShininess(1).setKr(new Double3(0.2))),
                new Cylinder(new Ray(new Point(0, 2.7, 0.6), new Vector(0, 0, 1),
                        new Vector(1, 0, 0)), 1, 0.2)
                        .setEmission(new Color(0, 10, 100)).setMaterial(new Material()
                                .setKd(0.2).setKs(0.1).setShininess(1).setKr(new Double3(0.2))),
                new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 0))
                        .setEmission(new Color(40, 40, 40)).setMaterial(new Material().setKd(0.5).setKs(0.5)
                                .setShininess(10).setKr(new Double3(0.4))));

        scene.lights.add(new SpotLight(new Color(255, 255, 255), new Point(3, 3, 3),
                new Vector(-1, -0.25, 0)).setLengthOfTheSide(4).setSoftShadowsRays(30));

        camera.setImageWriter(new ImageWriter("Poker with Anti-aliasing with soft shadows", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }
}
