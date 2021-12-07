package com.gamespace.assets;

public class Player {

    private final String name;
    private final PlayerID _id;
    private final String username;

    public Player(String name, PlayerID _id, String username){
        this.name = name;
        this._id = _id;
        this.username = username;
    }


    public int getCharacter(){
        if (this.get_id().equals(PlayerID.Player1))
            return 1;
        else
            return -1;
    }

    public PlayerID get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

}
