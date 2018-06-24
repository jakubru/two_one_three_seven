package pope.two_one_three_seven.model;

import java.util.ArrayList;
import java.util.List;

public class Point {

    double x;
    double y;
    int ID;
    boolean isOnCircle;
    List<Line> linesContaingPoint;

    public Point(double x, double y, int ID, boolean isOnCircle) {
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.isOnCircle = isOnCircle;
        this.linesContaingPoint = new ArrayList<>();
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public int getID() {
        return this.ID;
    }

    public boolean isOnCircle() {
        return isOnCircle;
    }

    public List<Line> getLinesContaingPoint() {
        return linesContaingPoint;
    }

    public void addLineContaingPoint(Line line) {
        linesContaingPoint.add(line);
    }

}
