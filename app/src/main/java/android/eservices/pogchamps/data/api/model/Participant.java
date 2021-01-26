package android.eservices.pogchamps.data.api.model;

public class Participant {
    private int id;
    private Tournament tournament;
    private int points;
    private int played;
    private Character groupe;
    private Player player;

    public int getId() {
        return id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public int getPoints() {
        return points;
    }

    public int getPlayed() {
        return played;
    }

    public Character getGroupe() {
        return groupe;
    }

    public Player getPlayer() {
        return player;
    }
}
