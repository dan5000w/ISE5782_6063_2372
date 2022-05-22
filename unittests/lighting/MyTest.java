package lighting;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Test rendering a basic image
 *
 * @author Daniel Wolpert & Amitay Cahlon
 */
public class MyTest {
    private final Scene scene = new Scene("Test scene");

    @Test
    public void Test1() {
        Camera camera = new Camera(new Point(7, 5, 2), new Vector(0, 0, 1), new Vector(-1, -0.5, 0)) //
                .setVPSize(1, 1).setVPDistance(1);

        scene.setBackground(new Color(0, 150, 210));

        scene.geometries.add( //
                new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 0))
                        .setEmission(new Color(40, 40, 40)).setMaterial(new Material().setKd(0.5).setKs(0.5)
                                .setShininess(50).setKr(new Double3(0.95))
                        )
                , new Triangle(Point.ZERO, new Point(0, 0, 1), new Point(1, 0, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300).setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(Point.ZERO, new Point(0, 0, 1), new Point(0, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300).setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(1, 0, 0), new Point(0, 0, 1), new Point(1, 0, 1))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300).setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(0, 1, 0), new Point(0, 0, 1), new Point(0, 1, 1))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300).setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(0, 1, 0), new Point(0, 1, 1), new Point(1, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300).setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(0, 1, 1), new Point(1, 1, 1), new Point(1, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300).setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(1, 0, 1), new Point(1, 0, 0), new Point(1, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300).setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(1, 0, 1), new Point(1, 1, 1), new Point(1, 1, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300).setKr(new Double3(0.1))).setEmission(new Color(110, 110, 100)),
                new Triangle(new Point(1, 0, 1), new Point(0, 1, 1), new Point(0, 0, 1))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300).setKr(new Double3(0.1))),
                new Triangle(new Point(1, 0, 1), new Point(0, 1, 1), new Point(1, 1, 1)).setEmission(new Color(110, 110, 100))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300).setKr(new Double3(0.1))),

                //new Sphere(new Point(0.5, 1, 0.5), 0.05).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(0.5, 1, 0.5),new Vector(0,1,0),new Vector(0,0,1)), 0.05, 0.001).setEmission(Color.BLACK),

                //new Sphere(new Point(1, 0.8, 0.8), 0.05).setEmission(Color.BLACK),
                //new Sphere(new Point(1, 0.2, 0.2), 0.05).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(1, 0.8, 0.8),new Vector(1,0,0),new Vector(0,1,0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(1, 0.2, 0.2),new Vector(1,0,0),new Vector(0,1,0)), 0.05, 0.001).setEmission(Color.BLACK),

                //new Sphere(new Point(0.5, 0.5, 1), 0.05).setEmission(Color.BLACK),
                //new Sphere(new Point(0.8, 0.8, 1), 0.05).setEmission(Color.BLACK),
                //new Sphere(new Point(0.2, 0.2, 1), 0.05).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(0.5, 0.5, 1),new Vector(0,0,1),new Vector(0,1,0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(0.8, 0.8, 1),new Vector(0,0,1),new Vector(0,1,0)), 0.05, 0.001).setEmission(Color.BLACK),
                new Cylinder(new Ray(new Point(0.2, 0.2, 1),new Vector(0,0,1),new Vector(0,1,0)), 0.05, 0.001).setEmission(Color.BLACK),


                new Cylinder(new Ray(new Point(0, 2.4, 0), new Vector(0, 0, 1), new Vector(1, 0, 0)), 1, 0.2)
                        .setEmission(new Color(100, 10, 10)).setMaterial(new Material().setKd(0.2).setKs(0.1).setShininess(1).setKr(new Double3(0.2))),
                new Cylinder(new Ray(new Point(0, 2.8, 0.2), new Vector(0, 0, 1), new Vector(1, 0, 0)), 1, 0.2)
                        .setEmission(new Color(0, 10, 100)).setMaterial(new Material().setKd(0.2).setKs(0.1).setShininess(1).setKr(new Double3(0.2))),
                new Cylinder(new Ray(new Point(0, 2.2, 0.4), new Vector(0, 0, 1), new Vector(1, 0, 0)), 1, 0.2)
                        .setEmission(new Color(100, 10, 10)).setMaterial(new Material().setKd(0.2).setKs(0.1).setShininess(1).setKr(new Double3(0.2))),
                new Cylinder(new Ray(new Point(0, 2.7, 0.6), new Vector(0, 0, 1), new Vector(1, 0, 0)), 1, 0.2)
                        .setEmission(new Color(0, 10, 100)).setMaterial(new Material().setKd(0.2).setKs(0.1).setShininess(1).setKr(new Double3(0.2))),

                new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 0))
                        .setEmission(new Color(40, 40, 40)).setMaterial(new Material().setKd(0.5).setKs(0.5)
                                .setShininess(10).setKr(new Double3(0.4))));

        scene.lights.add(new SpotLight(new Color(255, 255, 255), new Point(3, 3, 3), new Vector(-1, -0.25, 0)));

        camera.setImageWriter(new ImageWriter("MyTest", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }


    @Test
    public void Test2() {
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point(7, 0, 1), new Vector(0, 0, 1), new Vector(-1, 0, 0)) //
                .setVPSize(1, 1).setVPDistance(1).setNumOfRays(8);
        scene.setBackground(new Color(0, 150, 210));
        scene.geometries.add(
                new Sphere(new Point(-1, 0, 1), 1)
                        .setEmission(new Color(0, 0, 200)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(40)),
                new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 0))
                        .setEmission(new Color(40, 40, 40)).setMaterial(new Material().setKd(0.5).setKs(0.5)

                                .setShininess(50).setKr(new Double3(0.95))
                        ), new Triangle(new Point(-1, 0, 3), new Point(1, -2, 0), new Point(-3, -2, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)),
                new Triangle(new Point(-1, 0, 3), new Point(1, 2, 0), new Point(-3, 2, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)),
                new Triangle(new Point(-1, 0, 3), new Point(-3, -2, 0), new Point(-3, 2, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)),
                new Triangle(new Point(-1, 0, 3), new Point(1, -2, 0), new Point(1, 2, 0))
                        .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300).setKt(new Double3(0.5))),
                new Sphere(new Point(0, -1, 0.5), 0.5)
                        .setEmission(new Color(0, 200, 0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(40).setKr(new Double3(0.5))),
                new Sphere(new Point(0, 1, 0.5), 0.5)
                        .setEmission(new Color(200, 0, 0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(40).setKr(new Double3(0.3)))
        );
        scene.lights.add(new SpotLight(new Color(255, 255, 255), new Point(3, 3, 3), new Vector(-1, -0.04, 0)));

        camera.setImageWriter(new ImageWriter("MyTestsWithSoftShadow", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }
}
