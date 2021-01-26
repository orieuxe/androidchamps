package android.eservices.webrequests.data.api.model;

import java.util.Date;
import java.util.List;

public class Match {
    private int id;
    private Participant participant1;
    private Participant participant2;
    private List<Game> games;
    private String result;
    private String round;
    private Date date;
    private String stage;
    private Match next_duel;
    private Tournament tournament;

    public int getId() {
        return id;
    }

    public Participant getParticipant1() {
        return participant1;
    }

    public Participant getParticipant2() {
        return participant2;
    }

    public String getResult() {
        return result;
    }

    public String getRound() {
        return round;
    }

    public Date getDate() {
        return date;
    }

    public String getStage() {
        return stage;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public List<Game> getGames() {
        return games;
    }

    public Match getNextMatch(){
        return this.next_duel;
    }
}
