package pope.two_one_three_seven.model;

public class Point {

    double x;
    double y;
    int ID;
    boolean isOnCircle;

    public Point(double x, double y, int ID, boolean isOnCircle){
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.isOnCircle = isOnCircle;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public int getID(){
        return this.ID;
    }

    public boolean isOnCircle() {
        return isOnCircle;
    }


}
