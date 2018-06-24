package pope.two_one_three_seven.model;

import java.util.ArrayList;
import java.util.List;

public class LineTest {
    @org.junit.Test
    public void sortTest() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(10, 10, 4, true));
        points.add(new Point(-10, -10, 0, true));
        points.add(new Point(0, 2, 2, false));
        points.add(new Point(4, 1, 3, false));
        points.add(new Point(0, 1, 1, false));

        Line line = new Line(points);
        line.printPoints();

        line.sortPoints();

        line.printPoints();

    }
}