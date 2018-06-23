package pope.two_one_three_seven.model;

import java.util.List;

public class Field {

    Circle circle;
    List<Line> lines;

    public Field(Circle circle, List<Line> lines){
        this.circle = circle;
        this.lines = lines;
    }

    public Circle getCircle(){
        return this.circle;
    }

    public List<Line> getLines(){
        return this.lines;
    }
}
