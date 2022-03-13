package geometries;

import primitives.Point;

/**
 * Triangle class represents three-dimensional Triangle in 3D Cartesian coordinate
 * system
 * @author DW, AC
 */
public class Triangle extends Polygon{

    /**
     * Set values for triangle fields
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }
}
