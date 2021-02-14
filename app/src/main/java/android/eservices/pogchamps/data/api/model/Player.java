package android.eservices.pogchamps.data.api.model;

import java.io.Serializable;

public class Player implements Serializable {
    private int id;
    private String twitch;
    private String username;

    public int getId() {
        return id;
    }

    public String getTwitch() {
        return twitch;
    }

    public String getUsername() {
        return username;
    }

    public Object getIcon(){
        return "https://pogchamps.chess.com/assets/player/"+this.twitch+".png";
    }
}

