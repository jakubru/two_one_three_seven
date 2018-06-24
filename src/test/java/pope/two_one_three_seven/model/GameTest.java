package pope.two_one_three_seven.model;

import org.junit.Assert;

/**
 * Created by esromic on 2018-06-24.
 */
public class GameTest {
    @org.junit.Test
    public void generateRandomLineTest() {
        Game game = new Game();
        Line line = game.generateRandomLine();
        line.printPoints();
        for (Point p : line.getPointList()) {
            System.out.println("Point ID: " + p.getID() + "; length from the center = "
                    + Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY()));
        }
    }

    @org.junit.Test
    public void getCrossingPointTest() {
        Game game = new Game();
        // X and Y lines
        Line lineX = game.generateXLine();
        Line lineY = game.generateYLine();
        Point point = game.getCrossingPoint(lineX, lineY);
        Assert.assertTrue(point.getX() == 0.0 && point.getY() == 0.0);
        // lines with starting in the same point
        Line line1 = new Line();
        Line line2 = new Line();
        Point p1 = new Point(2.0, 3.0, 0, false);
        Point p2 = new Point(-1.0, 8.0, 0, false);
        Point p3 = new Point(7.0, -10.0, 0, false);

        line1.addPoint(p1);
        line1.addPoint(p2);
        line1.sortPoints();

        line2.addPoint(p1);
        line2.addPoint(p3);
        line2.sortPoints();

        Point pointP = game.getCrossingPoint(line1, line2);
        Assert.assertTrue(pointP.getX() == 2.0 && pointP.getY() == 3.0);
        // parallel lines
        Point pp1 = new Point(0.0, 3.0, 0, false);
        Point pp2 = new Point(2.0, 3.0, 0, false);
        Line parallel1 = game.generateXLine();
        parallel1.sortPoints();
        Line parallel2 = new Line();
        parallel2.addPoint(pp1);
        parallel2.addPoint(pp2);
        parallel2.sortPoints();

        Point parallelCross = game.getCrossingPoint(parallel1, parallel2);

        Assert.assertNull(parallelCross);
    }
}
