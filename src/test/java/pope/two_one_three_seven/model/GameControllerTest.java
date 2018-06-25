package pope.two_one_three_seven.model;

import org.junit.Assert;
import pope.two_one_three_seven.controller.GameController;

/**
 * Created by esromic on 2018-06-24.
 */
public class GameControllerTest {
    @org.junit.Test
    public void generateRandomLineTest() {
        GameController gameController = new GameController();
        Line line = gameController.generateRandomLine();
        line.printPoints();
        for (Point p : line.getPointList()) {
            System.out.println("Point ID: " + p.getID() + "; length from the center = "
                    + Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY()));
        }
    }

    @org.junit.Test
    public void getCrossingPointTest() {
        GameController gameController = new GameController();
        // X and Y lines
        Line lineX = gameController.generateXLine();
        Line lineY = gameController.generateYLine();
        Point point = gameController.getCrossingPointInCircle(lineX, lineY);
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

        Point pointP = gameController.getCrossingPointInCircle(line1, line2);
        Assert.assertTrue(pointP.getX() == 2.0 && pointP.getY() == 3.0);
        // parallel lines
        Point pp1 = new Point(0.0, 3.0, 0, false);
        Point pp2 = new Point(2.0, 3.0, 0, false);
        Line parallel1 = gameController.generateXLine();
        parallel1.sortPoints();
        Line parallel2 = new Line();
        parallel2.addPoint(pp1);
        parallel2.addPoint(pp2);
        parallel2.sortPoints();

        Point parallelCross = gameController.getCrossingPointInCircle(parallel1, parallel2);

        Assert.assertNull(parallelCross);
    }

    @org.junit.Test
    public void generateFieldTest() {
        GameController gameController = new GameController();
        gameController.addPlayer("adam");
        gameController.addPlayer("ewa");
        gameController.addPlayer("kuku");
        gameController.generateField(10);
        for (Line l : gameController.getField().getLines()) {
            System.out.println("Line:");
            l.printPoints();
        }
        System.out.println();
        for (Player p : gameController.mListOfPlayers) {
            System.out.println("Player: " + p.getNick() + " stands on: "
                    + p.getPoint().getX() + "; " + p.getPoint().getY() + " at the distance from the center: "
                    + Math.sqrt(p.getPoint().getX() * p.getPoint().getX()
                    + p.getPoint().getY() * p.getPoint().getY()));
        }
    }

}
