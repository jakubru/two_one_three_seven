package pope.two_one_three_seven.model;

public class Point {

    int x;
    int y;
    int ID;
    boolean isOnCircle;

    public Point(int x, int y, int ID, boolean isOnCircle){
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.isOnCircle = isOnCircle;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getID(){
        return this.ID;
    }

    public boolean isOnCircle() {
        return isOnCircle;
    }


}
