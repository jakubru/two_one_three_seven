package pope.two_one_three_seven.model;

public class Player {
    String mNick;
    boolean mIsActive;

    Player(String nick){
        this.mNick = nick;
        mIsActive = true;
    }

    void deactivate(){
        mIsActive = false;
    }

    void activate(){
        mIsActive = true;
    }

    void changeNick(String nick){
        mNick = nick;
    }

    boolean isActive(){
        return mIsActive;
    }

    public String getNick(){
        return mNick;
    }
}
