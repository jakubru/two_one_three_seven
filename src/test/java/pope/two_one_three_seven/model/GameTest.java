package pope.two_one_three_seven.model;

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
}
