package pope.two_one_three_seven.model;


import java.util.LinkedList;

public class ListOfPlayers {
    LinkedList<Player> mListOfPlayers;
    private static ListOfPlayers mInstance;


    private ListOfPlayers(){
        mListOfPlayers = new LinkedList<>();
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
        if(mListOfPlayers.size() < 5){
            mListOfPlayers.add(new Player(nick));
            return true;
        }
        return false;
    }

    public void removePlayer(String nick){
        for(int i = 0; i < mListOfPlayers.size();i++)
            if(mListOfPlayers.get(i).getNick() == nick)
                mListOfPlayers.remove(i);
    }

    public Player getPlayer(String nick){
        for(int i = 0; i < mListOfPlayers.size();i++)
            if(mListOfPlayers.get(i).getNick() == nick)
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
}
