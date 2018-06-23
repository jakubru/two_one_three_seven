package pope.two_one_three_seven.model;

import java.util.ArrayList;
import java.util.List;

public class Line {

    List<Point> points;

    public Line(List<Point> points){
        this.points = points;
    }

    public List<Point> getPointList(){
        return this.points;
    }

}
