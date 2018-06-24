package pope.two_one_three_seven.model;

public class Circle {

    Point mid;
    double radius;

    public Circle(Point p, double k){
        this.mid = p;
        this.radius = k;
    }

    public Point getMid(){
        return this.mid;
    }

    public double getRadius(){
        return this.radius;
    }

}
