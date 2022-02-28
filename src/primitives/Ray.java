package primitives;

import java.util.Objects;

public class Ray {
    private final Point p0;
    private final Vector dir;

    public Ray(Point p, Vector v){
        p0 = p;
        dir = v.normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public String toString() {
        return "Ray: " +
                "p0=" + p0 +
                ", dir=" + dir;
    }
}
