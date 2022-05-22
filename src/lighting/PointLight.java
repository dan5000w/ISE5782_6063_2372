package lighting;

import geometries.Plane;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.randomDoubleBetweenTwoNumbers;

/**
 * Point light class represents a non-directional light source with a position
 */
public class PointLight extends Light implements LightSource {

    /**
     * The points position
     */
    protected final Point position;

    /**
     * An attenuation coefficient of the light source
     */
    protected double kC = 1;

    /**
     * An attenuation coefficient of the light source
     */
    protected double kL = 0;

    /**
     * An attenuation coefficient of the light source
     */
    protected double kQ = 0;

    /**
     * square edge size parameter
     */
    private int lengthOfTheSide;

    /**
     * The amount of rays of the soft shadow.
     * (set 0 to `turn off` the action)
     */
    public static int softShadowsRays;

    /**
     * Setter of the square edge size parameter
     *
     * @param lengthOfTheSide square edge size
     * @return the updated point light
     */
    public PointLight setLengthOfTheSide(int lengthOfTheSide) {
        if (lengthOfTheSide < 0)
            throw new IllegalArgumentException("LengthOfTheSide must be greater then 0");
        this.lengthOfTheSide = lengthOfTheSide;
        return this;
    }

    /**
     * Set the number of `soft shadows` rays
     *
     * @param numOfRays the number of `soft shadows` rays
     * @return the updated camera object
     */
    public PointLight setSoftShadowsRays(int numOfRays) {
        if (numOfRays < 0)
            throw new IllegalArgumentException("numOfRays must be greater then 0!");
        softShadowsRays = numOfRays;
        return this;
    }

    /**
     * Constructor for point-light, sets position value and uses super constructor for intensity's value
     *
     * @param intensity the point lights intensity
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
        softShadowsRays = 0;
        lengthOfTheSide = 0;
    }


    /**
     * Setter of the light source's attenuation coefficient kC
     *
     * @param kC constant attenuation factor
     * @return the updated point light
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Setter of the light source's attenuation coefficient kL
     *
     * @param kL constant attenuation factor
     * @return the updated point light
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Setter of the light source's attenuation coefficient kQ
     *
     * @param kQ constant attenuation factor
     * @return the updated point light
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double d = p.distance(position);
        // iL = I0 / kC + kL * d + kQ * d^2
        return intensity.scale(1 / (kC + kL * d + kQ * d * d));
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point p) {
        return p.distance(position);
    }

    @Override
    public List<Vector> getL2(Point p) {
        if (lengthOfTheSide == 0) return List.of(getL(p));
        List<Vector> vectors = new LinkedList<>();
        // help vectors
        Vector v0, v1;
        // A variable that tells how many divide each side
        double divided = Math.sqrt(softShadowsRays);
        // plane of the light
        Plane plane = new Plane(position, getL(p));
        // vectors of the plane
        List<Vector> vectorsOfThePlane = findVectorsOfPlane(plane);
        // Starting point of the square around the lighting
        Point startPoint = position.add(vectorsOfThePlane.get(0).normalize().scale(-lengthOfTheSide / 2.0))
                .add(vectorsOfThePlane.get(1).normalize().scale(-lengthOfTheSide / 2.0));

        // A loop that runs as the number of vectors and in each of its runs it brings a vector around the lamp
        for (double i = 0; i < lengthOfTheSide; i += lengthOfTheSide / divided) {
            for (double j = 0; j < lengthOfTheSide; j += lengthOfTheSide / divided) {
                v0 = vectorsOfThePlane.get(0).normalize()
                        .scale(randomDoubleBetweenTwoNumbers(i, i + lengthOfTheSide / divided));
                v1 = vectorsOfThePlane.get(1).normalize()
                        .scale(randomDoubleBetweenTwoNumbers(j, j + lengthOfTheSide / divided));
                vectors.add(p.subtract(startPoint.add(v0).add(v1)).normalize());
            }
        }
        return vectors;
    }

    private List<Vector> findVectorsOfPlane(Plane plane) {
        List<Vector> vectors = new LinkedList<>();
        double nX = plane.getNormal().getX(), nY = plane.getNormal().getY(), nZ = plane.getNormal().getZ();
        double pX = plane.getP0().getX(), pY = plane.getP0().getY(), pZ = plane.getP0().getZ();
        double d = -(nX * pX + nY * pY + nZ * pZ);
        Point p0 = plane.getP0();
        int amount = 0;
        // we calculate a point on the plane, and then we create a vector with the point
        if (nX != 0) {
            double x1 = (d / nX);
            vectors.add((new Point(x1, 0, 0)).subtract(p0));
            amount++;
        }
        if (nY != 0) {
            double y2 = (d / nY);
            vectors.add((new Point(0, y2, 0)).subtract(p0));
            amount++;
        }
        if (nZ != 0 && amount < 2) {
            double z3 = (d / nZ);
            vectors.add((new Point(0, 0, z3)).subtract(p0));
        }
        return vectors;
    }
}
