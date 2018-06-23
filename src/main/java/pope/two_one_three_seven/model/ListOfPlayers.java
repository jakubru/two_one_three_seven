package pope.two_one_three_seven.model;


import java.util.LinkedList;

public class ListOfPlayers {
    LinkedList<Player> mListOfPlayers;
    private static ListOfPlayers mInstance;
    private int active;

    private ListOfPlayers(){
        mListOfPlayers = new LinkedList<Player>();
    }

    public static ListOfPlayers getInstance(){
        if (mInstance == null){
            mInstance = new ListOfPlayers();
        }
        return mInstance;
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

    public String display(){
        String ret = "";
        for(int i = 0; i < mListOfPlayers.size(); i++){
            ret = ret + mListOfPlayers.get(i).getNick() + " ";
        }
        return ret;
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
}
