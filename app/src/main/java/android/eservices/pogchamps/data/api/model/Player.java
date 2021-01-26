package android.eservices.pogchamps.data.api.model;

public class Player {
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

    public String getIconUrl(){
        return "https://pogchamps.chess.com/assets/player/"+this.twitch+".png";
    }
}

