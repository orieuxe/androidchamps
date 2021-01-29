package android.eservices.pogchamps.data.api.model;

import com.github.bhlangonijr.chesslib.game.Game;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Match implements Serializable {
    private int id;
    private Participant participant1;
    private Participant participant2;
    private String result;
    private String round;
    private Date date;
    private String stage;
    private Match next_duel;

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

    public Match getNextMatch(){
        return this.next_duel;
    }
}
