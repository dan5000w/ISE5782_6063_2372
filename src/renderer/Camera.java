package renderer;

import geometries.Intersectable;
import primitives.*;


import java.util.List;

import static primitives.Util.isZero;

public class Camera {

    private Point p0;
    private Vector vRight;
    private Vector vUp;
    private Vector vTo;
    private double height;
    private double width;
    private double distance;

    /**
     * Constructor of Camera using p0, up-vector and to-vector
     *
     * @param p0  the point that the camera is on
     * @param vUp the vector of the direction that the is "looking up" from the camera
     * @param vTo the vector of the direction that the camera is looking at
     */
    public Camera(Point p0, Vector vUp, Vector vTo) {
        this.p0 = p0;
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        if (isZero(vTo.dotProduct(vUp))) {
            this.vRight = vTo.crossProduct(vUp).normalize();
        } else
            throw new IllegalArgumentException("vUp and vTo arent orthogonal!");
    }

    /**
     * Updates the size of the view plane
     *
     * @param width  the new width of the view plane
     * @param height the new width of the view plane
     * @return the updated camera object
     */
    public Camera setVPSize(double width, double height) {
        this.height = height;
        this.width = width;
        return this;
    }

    /**
     * Updates the distance between the camera and the view plane
     *
     * @param distance the new distance
     * @return the updated camera object
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * Gets the height of the view plane
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets the width of the view plane
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets the distance between the __ and the (*&
     *
     * @return the distance
     */
    public double getDistance() {
        return distance;
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
        Point pIJ = p0.add(vTo.scale(distance));
        // Ratio
        double rX = width / nX;
        double rY = height / nY;
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
