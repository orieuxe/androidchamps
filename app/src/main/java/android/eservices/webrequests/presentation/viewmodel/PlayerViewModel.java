package android.eservices.webrequests.presentation.viewmodel;

import android.eservices.webrequests.data.api.model.Player;

public class PlayerViewModel {
    private int id;
    private String twitch;
    private String username;

    public PlayerViewModel(Player winner) {
        this.id = winner.getId();
        this.twitch = winner.getTwitch();
        this.username = winner.getUsername();
    }

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

