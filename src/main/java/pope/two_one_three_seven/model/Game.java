package pope.two_one_three_seven.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    List<Player> mListOfPlayers;
    Field mField;

    public Field getField(){
        return this.mField;
    }

    public Game(){
        this.mListOfPlayers = new ArrayList<Player>();
        generateField();
    }


    public boolean addPlayer(String nick){
        for(int i = 0; i < mListOfPlayers.size(); i++){
            if(mListOfPlayers.get(i).getNick().equals(nick))
                return false;
        }
        if(mListOfPlayers.size() < 3){
            mListOfPlayers.add(new Player(nick));
            if(getSize() == 1){
                mListOfPlayers.get(0).activate();
            }
            return true;
        }
        return false;
    }


    public Player getPlayer(String nick){
        for(int i = 0; i < mListOfPlayers.size();i++)
            if(mListOfPlayers.get(i).getNick().equals(nick))
                return mListOfPlayers.get(i);
        return null;
    }


    public int getSize(){
        return mListOfPlayers.size();
    }

    public Player getNext(String nick){
        for(int i = 0; i < mListOfPlayers.size();i++)
            if(mListOfPlayers.get(i).getNick().equals( nick))
                return mListOfPlayers.get((i+1)%3);
        return null;
    }

    private void generateField(){
        Point p1 = new Point(1,1,1,true);
        Point p2 = new Point(2,1,2,false);
        Point p3 = new Point(3,1,3,true);
        Point p4 = new Point(4,1,4,true);
        Point p5 = new Point(5,1,5,true);
        Point p6 = new Point(6,1,6,true);
        Point p7 = new Point(8,1,7,false);
        List<Point> points1 = new ArrayList<Point>();
        points1.add(p1);
        points1.add(p2);
        points1.add(p3);
        List<Point> points2 = new ArrayList<Point>();
        points2.add(p4);
        points2.add(p5);
        List<Point> points3 = new ArrayList<Point>();
        points3.add(p6);
        points3.add(p7);
        Line l1 = new Line(points1);
        Line l2 = new Line(points2);
        Line l3 = new Line(points3);
        List<Line> lines = new ArrayList<Line>();
        lines.add(l1);
        lines.add(l2);
        lines.add(l3);
        Circle c = new Circle(p1,7);
        this.mField = new Field(c,lines);
        /*W tej funkcji wszystko ustawiane, razem z początkowymi punktami graczy*/
    }

    private boolean checkNeighbours(int ID){
        return true;
        /*Funkcja do sprawdzania sąsiadów punktów*/
    }

    private boolean isOccupied(int pointID){
        boolean flag = false;
        for(int i = 0; i < mListOfPlayers.size(); i++){
            if(mListOfPlayers.get(i).getPointID() == pointID){
                flag = true;
            }
        }
        return flag;
    }

    public boolean makeMove(String nick, int pointID){
        if(getPlayer(nick).isActive() && this.checkNeighbours(pointID) && !this.isOccupied(pointID)){
            this.getPlayer(nick).deactivate();
            this.getPlayer(nick).setPointID(pointID);
            this.getNext(nick).activate();
            return true;
        }
        return false;
    }
}
