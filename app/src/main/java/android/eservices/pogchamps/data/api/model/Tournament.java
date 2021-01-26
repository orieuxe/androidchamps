package android.eservices.pogchamps.data.api.model;

import java.io.Serializable;

public class Tournament implements Serializable {
    private int id;
    private Player winner;

    public int getId() {
        return id;
    }

    public Player getWinner() {
        return winner;
    }
}
