package pope.two_one_three_seven.model;

import java.util.ArrayList;
import java.util.List;

public class Line {

    List<Point> points;

    public Line() {
        this.points = new ArrayList<>();
    }

    public Line(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPointList() {
        return this.points;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void sortPoints() {
        points.sort((o1, o2) -> (o1.getX() != o2.getX()) ?
                (Double.compare(o1.getX(), o2.getX())) : (Double.compare(o1.getY(), o2.getY())));
    }

    public void printPoints() {
        for (Point p : points) {
            System.out.print("x: " + p.getX() + " y: " + p.getY() + "; ");
        }
        System.out.println();
    }

}
