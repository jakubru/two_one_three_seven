package pope.two_one_three_seven.model;

public class Circle {

    Point mid;
    int radius;

    public Circle(Point p, int k){
        this.mid = p;
        this.radius = k;
    }

    public Point getMid(){
        return this.mid;
    }

    public int getRadius(){
        return this.radius;
    }

}
