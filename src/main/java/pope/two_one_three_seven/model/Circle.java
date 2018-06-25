package pope.two_one_three_seven.model;

import static java.lang.Math.round;

public class Circle {

    Point mid;
    double radius;

    public Circle(Point p, double k) {
        this.mid = p;
        this.radius = k;
    }

    public Point getMid() {
        return this.mid;
    }

    public double getRadius() {
        return this.radius;
    }

    public int scaledR(int scale) {
        return (int) round(radius * (double) scale);
    }
}
