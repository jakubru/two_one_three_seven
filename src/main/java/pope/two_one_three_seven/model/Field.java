package pope.two_one_three_seven.model;

public class Field {
    int something;
    private Field(int n){
        this.something = n;
    }

    public int getSomething(){
        return this.something;
    }

    private static Field mField;

    public static Field getInstance(){
        if(mField == null){
            mField = new Field(5);
        }
        return mField;
    }
}
