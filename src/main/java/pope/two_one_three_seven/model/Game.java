package pope.two_one_three_seven.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.PI;

public class Game {
    List<Player> mListOfPlayers;
    Field mField;
    int crrPointId = 0;

    public Field getField() {
        return this.mField;
    }

    public Game() {
        this.mListOfPlayers = new ArrayList<Player>();
        generateField(100);
    }

    public Game(int numOfLines) {
        this.mListOfPlayers = new ArrayList<Player>();
        generateField(numOfLines);
    }

    public boolean addPlayer(String nick) {
        for (int i = 0; i < mListOfPlayers.size(); i++) {
            if (mListOfPlayers.get(i).getNick().equals(nick))
                return false;
        }
        if (mListOfPlayers.size() < 3) {
            mListOfPlayers.add(new Player(nick));
            if (getSize() == 1) {
                mListOfPlayers.get(0).activate();
            }
            return true;
        }
        return false;
    }


    public Player getPlayer(String nick) {
        for (int i = 0; i < mListOfPlayers.size(); i++)
            if (mListOfPlayers.get(i).getNick().equals(nick))
                return mListOfPlayers.get(i);
        return null;
    }


    public int getSize() {
        return mListOfPlayers.size();
    }

    public Player getNext(String nick) {
        for (int i = 0; i < mListOfPlayers.size(); i++)
            if (mListOfPlayers.get(i).getNick().equals(nick))
                return mListOfPlayers.get((i + 1) % 3);
        return null;
    }

    private void generateField(int numOfLines) {
        Circle c = generateCircle();
        mField = new Field(c);
        mField.addLine(generateXLine());
        Line lineY = generateYLine();
        mField.addLine(lineY);
        updateCrossingLines(lineY);

        for (int i = 0; i < numOfLines; ++i) {
            Line randLine = generateRandomLine();
            updateCrossingLines(randLine);
            mField.addLine(randLine);
        }

        for (Player player : mListOfPlayers) {
            player.setPoint(getRandomPointOnCircle());
        }
        /*W tej funkcji wszystko ustawiane, razem z początkowymi punktami graczy*/
        // chosing the line and its on-circle-point for each player randomly
    }

    private boolean checkNeighbours(Point point, Player player) {
        for (Line l : point.getLinesContaingPoint()) {
            int index = l.getPointList().indexOf(point);
            if (index == 0 && player.getPoint().equals(l.getPointList().get(index + 1))) {
                return true;
            } else if (index == l.getPointList().size() - 1
                    && player.getPoint().equals(l.getPointList().get(index - 1))){
                return true;
            } else {
                if (player.getPoint().equals(l.getPointList().get(index - 1))
                        || player.getPoint().equals(l.getPointList().get(index + 1))) {
                    return true;
                }
            }
            return false;
        }
        return true;
        /*Funkcja do sprawdzania sąsiadów punktów*/
    }

    private boolean isOccupied(int pointID) {
        boolean flag = false;
        for (Player player : mListOfPlayers) {
            if (player.getPoint().getID() == pointID)
                flag = true;
        }
        return flag;
    }

    public boolean makeMove(String nick, int pointID) {
        Point point = getPointById(pointID);
        if (getPlayer(nick).isActive() && this.checkNeighbours(point, getPlayer(nick)) && !this.isOccupied(pointID)) {
            this.getPlayer(nick).deactivate();
            this.getPlayer(nick).setPoint(point);
            this.getNext(nick).activate();
            return true;
        }
        return false;
    }

    public boolean didWin(Player player) {
        return player.getPoint().getX() == 0.0 && player.getPoint().getY() == 0.0;
    }

    public Circle generateCircle() {
        Point centralPoint = new Point(0.0, 0.0, this.crrPointId++, false);
        return new Circle(centralPoint, 1.0);
    }

    public Line generateXLine() {
        Point p1 = new Point(-1.0, 0.0, crrPointId++, true);
        Point p2 = new Point(1.0, 0.0, crrPointId++, true);
        Line line = new Line();
        line.addPoint(p1);
        line.addPoint(p2);
        p1.addLineContaingPoint(line);
        p2.addLineContaingPoint(line);
        return line;
    }

    public Line generateYLine() {
        Point p1 = new Point(0.0, -1.0, crrPointId++, true);
        Point p2 = new Point(0.0, 1.0, crrPointId++, true);
        Line line = new Line();
        line.addPoint(p1);
        line.addPoint(p2);
        p1.addLineContaingPoint(line);
        p2.addLineContaingPoint(line);
        return line;
    }

    public Line generateRandomLine() {
        Random rand = new Random();
        double rand1 = 2.0 * PI * rand.nextDouble();
        double rand2 = 2.0 * PI * rand.nextDouble();
        Point p1 = new Point(Math.cos(rand1), Math.sin(rand1), crrPointId++, true);
        Point p2 = new Point(Math.cos(rand2), Math.sin(rand2), crrPointId++, true);
        Line line = new Line();
        line.addPoint(p1);
        line.addPoint(p2);
        p1.addLineContaingPoint(line);
        p2.addLineContaingPoint(line);
        return line;
    }

    public Point getCrossingPointInCircle(Line l1, Line l2) {
        List<Point> pointsL1 = l1.getPointList();
        List<Point> pointsL2 = l2.getPointList();
        double xL1 = pointsL1.get(0).getX();
        double yL1 = pointsL1.get(0).getY();
        double dxL1 = pointsL1.get(pointsL1.size() - 1).getX() - pointsL1.get(0).getX();
        double dyL1 = pointsL1.get(pointsL1.size() - 1).getY() - pointsL1.get(0).getY();

        double xL2 = pointsL2.get(0).getX();
        double yL2 = pointsL2.get(0).getY();
        double dxL2 = pointsL2.get(pointsL2.size() - 1).getX() - pointsL2.get(0).getX();
        double dyL2 = pointsL2.get(pointsL2.size() - 1).getY() - pointsL2.get(0).getY();

        double tDenom = dxL1 * dyL2 - dyL1 * dxL2;
        if (tDenom != 0.0) {
            double dxL1L2 = xL2 - xL1;
            double dyL1L2 = yL2 - yL1;
            double t = (dxL1L2 * dyL2 - dyL1L2 * dxL2) / tDenom;
            double xP = xL1 + t * dxL1;
            double yP = yL1 + t * dyL1;
            if (isCoordInCircle(xP, yP))
                return new Point(xP, yP, crrPointId++, false);
        }
        return null;
    }

    public boolean isPointInCircle(Point point) {
        return Math.sqrt(point.getX() * point.getX() + point.getY() * point.getY()) < 1.0;
    }

    public boolean isCoordInCircle(double x, double y) {
        return Math.sqrt(x * x + y * y) < 1.0;
    }

    public void updateCrossingLines(Line line) {
        for (Line l2 : mField.getLines()) {
            Point crossingPoint = getCrossingPointInCircle(line, l2);
            if (crossingPoint != null) {
                line.addPoint(crossingPoint);
                l2.addPoint(crossingPoint);
                line.sortPoints();
                l2.sortPoints();
                crossingPoint.addLineContaingPoint(line);
                crossingPoint.addLineContaingPoint(l2);
            }
        }
    }

    public Point getRandomPointOnCircle() {
        Random rand = new Random();
        int linesNum = mField.getLines().size();
        Line randLine = mField.getLines().get(rand.nextInt(linesNum));
        int sideNum = rand.nextInt(2);
        List<Point> pointsList = randLine.getPointList();
        if (sideNum == 0) return pointsList.get(0);
        else return pointsList.get(pointsList.size() - 1);
    }

    public Point getPointById(int pointId) {
        for (Line l : mField.getLines()) {
            for (Point p : l.getPointList()) {
                if (p.getID() == pointId) {
                    return p;
                }
            }
        }
        return null;
    }
}
