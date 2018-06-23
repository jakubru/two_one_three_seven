package pope.two_one_three_seven.model;

public class Player {
    String mNick;
    boolean mIsActive;


    Player(String nick){
        this.mNick = nick;
        mIsActive = false;
    }

    public void deactivate(){
        mIsActive = false;
    }

    public void activate(){
        mIsActive = true;
    }


    public boolean isActive(){
        return mIsActive;
    }

    public String getNick(){
        return mNick;
    }
}
