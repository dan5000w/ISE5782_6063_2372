package renderer;

import primitives.*;

import java.util.MissingResourceException;

import static primitives.Util.*;

/**
 * Camera class represents a camera in 3D Cartesian coordinate system
 *
 * @author Daniel Wolpert, Amitay Cahalon
 */
public class Camera {

    private Point p0;
    private Vector vRight;
    private Vector vUp;
    private Vector vTo;
    private double heightVP;
    private double widthVP;
    private double distanceVP;
    private ImageWriter imageWriter;
    private RayTracerBasic rayTracer;

    /**
     * Constructor of Camera using p0, up-vector and to-vector
     *
     * @param p0  the point that the camera is on
     * @param vUp the vector of the direction that the is "looking up" from the camera
     * @param vTo the vector of the direction that the camera is looking at
     */
    public Camera(Point p0, Vector vUp, Vector vTo) {
        if (!isZero(vTo.dotProduct(vUp)))
            throw new IllegalArgumentException("vUp and vTo arent orthogonal!");
        this.p0 = p0;
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        this.vRight = vTo.crossProduct(vUp).normalize();
    }

    /**
     * Updates the size of the view plane
     *
     * @param width  the new width of the view plane
     * @param height the new width of the view plane
     * @return the updated camera object
     */
    public Camera setVPSize(double width, double height) {
        this.heightVP = height;
        this.widthVP = width;
        return this;
    }

    /**
     * Updates the distance between the camera and the view plane
     *
     * @param distance the new distance
     * @return the updated camera object
     */
    public Camera setVPDistance(double distance) {
        this.distanceVP = distance;
        return this;
    }

    /**
     * Renders the Image while throwing an exception if values are not initialized
     */
    public Camera renderImage() {
        if (heightVP == 0.0 || widthVP == 0.0 || distanceVP == 0.0 || imageWriter == null || rayTracer == null) {
            throw new MissingResourceException("ERROR", "Camera", "one of the key has not been initialized");
        }
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nX; ++i) {
            for (int j = 0; j < nY; ++j) {
                Color myColor = rayTracer.traceRay(constructRay(nX, nY, i, j));
                imageWriter.writePixel(i, j, myColor);
            }
        }
        return this;
    }

    /**
     * Updates the cameras image writer
     *
     * @param imageWriter responsible for holding image related parameters of View Plane
     * @return The updated camera
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * Updates the cameras ray tracer
     *
     * @param rayTracerBasic calculates the color of the point
     * @return The updated camera
     */
    public Camera setRayTracer(RayTracerBasic rayTracerBasic) {
        this.rayTracer = rayTracerBasic;
        return this;
    }

    /**
     * Prints the color on to the images grid
     *
     * @param interval the distance between the grid 'blocks'
     * @param color    the color of the grid
     */
    void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("ERROR", "Camera", "imageWriter");
        for (int i = 0; i < imageWriter.getNx(); ++i) {
            for (int j = 0; j < imageWriter.getNy(); ++j) {
                if (i % interval == 0 || j % interval == 0)
                    imageWriter.writePixel(i, j, color);
            }
        }
    }

    /**
     * Writes the data To the image file
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("ERROR", "Camera", "imageWriter");
        imageWriter.writeToImage();
    }

    /**
     * Gets the height of the view plane
     *
     * @return the height
     */
    public double getHeightVP() {
        return heightVP;
    }

    /**
     * Gets the width of the view plane
     *
     * @return the width
     */
    public double getWidth() {
        return widthVP;
    }

    /**
     * Gets the distance between the camera and the view plane
     *
     * @return the distance
     */
    public double getDistanceVP() {
        return distanceVP;
    }

    /**
     * Construct a ray from camera to specific pixel
     *
     * @param nX the number of pixels in each row
     * @param nY the number of pixels in each column
     * @param j  the column index of a pixel
     * @param i  the row index of a pixel
     * @return the ray
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        // pIJ = pCenter
        Point pIJ = p0.add(vTo.scale(distanceVP));
        // Ratio
        double rX = widthVP / nX;
        double rY = heightVP / nY;
        // xJ, yI
        double xJ = (j - (double) (nX - 1) / 2) * rX;
        double yI = -(i - (double) (nY - 1) / 2) * rY;
        // adding to pCenter
        if (!isZero(xJ)) pIJ = pIJ.add(vRight.scale(xJ));
        if (!isZero(yI)) pIJ = pIJ.add(vUp.scale(yI));
        // p0 = position of camera, dir = pIJ - p0.
        return new Ray(p0, pIJ.subtract(p0));
    }
}
