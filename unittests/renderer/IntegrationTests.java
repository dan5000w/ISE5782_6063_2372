package renderer;

import primitives.*;
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Vector;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Camera class and Geometries Integration tests
 */
public class IntegrationTests {

    final Camera camera = new Camera( //
            Point.ZERO, //
            new Vector(0, -1, 0), //
            new Vector(0, 0, 1)) //
            .setVPDistance(1) //
            .setVPSize(3, 3);
    int numOfIntersectionPoints = 0;

    @Test
    void CameraSphereIntersections() {
        // T01: sphere radius is 1, two intersection points
        Sphere sphere1 = new Sphere(new Point(0, 0, 3), 1);
        numOfIntersectionPoints = getNumberOfIntersections(sphere1);
        assertEquals(2, numOfIntersectionPoints, "wrong intersection points number!");

        // T02: sphere radius is 2.5, 18 intersection points
        Sphere sphere2 = new Sphere(new Point(0, 0, 3), 2.5);
        numOfIntersectionPoints = getNumberOfIntersections(sphere2);
        assertEquals(18, numOfIntersectionPoints, "wrong intersection points number!");

        // T03: sphere radius is 2, 10 intersection points
        Sphere sphere3 = new Sphere(new Point(0, 0, 2.5), 2);
        numOfIntersectionPoints = getNumberOfIntersections(sphere3);

        assertEquals(10, numOfIntersectionPoints, "wrong intersection points number!");

        // T04: sphere radius is 4, 9 intersection points (the camera is inside the sphere)
        Sphere sphere4 = new Sphere(new Point(0, 0, 1), 4);
        numOfIntersectionPoints = getNumberOfIntersections(sphere4);
        assertEquals(9, numOfIntersectionPoints, "wrong intersection points number!");

        // T05: sphere radius is 0.4, 0 intersection points (the sphere is behind the camera)
        Sphere sphere5 = new Sphere(new Point(0, 0, -1), 0.5);
        numOfIntersectionPoints = getNumberOfIntersections(sphere5);
        assertEquals(0, numOfIntersectionPoints, "wrong intersection points number!");
    }

    @Test
    void CameraPlaneIntersections() {
        // T01: the plane and the view plane are parallel, 9 intersection points
        Plane plane1 = new Plane(new Point(0, 0, 5), new Vector(0, 0, 1));
        numOfIntersectionPoints = getNumberOfIntersections(plane1);
        assertEquals(9, numOfIntersectionPoints, "wrong intersection points number!");

        // T02: the plane and the view plane are not parallel, 9 intersection points
        Plane plane2 = new Plane(new Point(0, -3, 1), new Vector(0, -0.5, 1));
        numOfIntersectionPoints = getNumberOfIntersections(plane2);
        assertEquals(9, numOfIntersectionPoints, "wrong intersection points number!");

        // T03: no intersection with the rays from the bottom row of the view plane, 6 intersection points
        Plane plane3 = new Plane(new Point(0, -3, 1), new Vector(0, -2, 1));
        numOfIntersectionPoints = getNumberOfIntersections(plane3);
        assertEquals(6, numOfIntersectionPoints, "wrong intersection points number!");
    }

    @Test
    void CameraTriangleIntersections() {
        // T01: intersect only with the canter ray, one intersection points
        Triangle triangle1 = new Triangle(new Point(0, -1, 2), new Point(1, 1, 2), new Point(-1, 1, 2));
        numOfIntersectionPoints = getNumberOfIntersections(triangle1);
        assertEquals(1, numOfIntersectionPoints, "wrong intersection points number!");

        // T02: intersect only with the canter ray and the above one, two intersection points
        Triangle triangle2 = new Triangle(new Point(0, -20, 2), new Point(1, 1, 2), new Point(-1, 1, 2));
        numOfIntersectionPoints = getNumberOfIntersections(triangle2);
        assertEquals(2, numOfIntersectionPoints, "wrong intersection points number!");
    }

    /**
     * Checks the amount of intersection points in a geometry from a camera.
     *
     * @param intersectable The geometry to check intersections with.
     * @return The amount of intersection points from the given camera.
     */
    int getNumberOfIntersections(Intersectable intersectable) {
        int sum = 0, vpXSize = 3, vpYSize = 3;
        for (int i = 0; i < vpXSize; ++i) {
            for (int j = 0; j < vpYSize; ++j) {
                List<Point> pointList = intersectable.findIntersections(camera.constructRay(vpXSize, vpYSize, j, i));
                if (pointList != null)
                    sum += pointList.size();
            }
        }
        return sum;
    }
}
