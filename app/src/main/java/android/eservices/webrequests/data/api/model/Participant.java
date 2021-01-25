package android.eservices.webrequests.data.api.model;

public class Participant {
    private int id;
    private Tournament tournament;
    private int points;
    private int played;
    private String group;
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

    public String getGroup() {
        return group;
    }

    public Player getPlayer() {
        return player;
    }
}
