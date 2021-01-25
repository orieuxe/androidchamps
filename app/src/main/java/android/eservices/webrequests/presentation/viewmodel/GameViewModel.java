package android.eservices.webrequests.presentation.viewmodel;

import android.eservices.webrequests.data.api.model.Game;

import java.util.Date;

public class GameViewModel {
    private int id;
    private String site;
    private Date date;
    private String white;
    private String black;
    private String result;
    private int whiteelo;
    private int blackelo;
    private String timecontrol;
    private String eco;
    private String termination;
    private int length;
    private String moves;
    private String clocks;

    public GameViewModel(Game game) {
        this.id = game.getId();
        this.site = game.getSite();
        this.date = game.getDate();
        this.white = game.getWhite();
        this.black = game.getBlack();
        this.result = game.getResult();
        this.whiteelo = game.getWhiteelo();
        this.blackelo = game.getBlackelo();
        this.timecontrol = game.getTimecontrol();
        this.eco = game.getEco();
        this.termination = game.getTermination();
        this.length = game.getLength();
        this.moves = game.getMoves();
        this.clocks = game.getClocks();
    }

    public int getId() {
        return id;
    }

    public String getSite() {
        return site;
    }

    public Date getDate() {
        return date;
    }

    public String getWhite() {
        return white;
    }

    public String getBlack() {
        return black;
    }

    public String getResult() {
        return result;
    }

    public int getWhiteelo() {
        return whiteelo;
    }

    public int getBlackelo() {
        return blackelo;
    }

    public String getTimecontrol() {
        return timecontrol;
    }

    public String getEco() {
        return eco;
    }

    public String getTermination() {
        return termination;
    }

    public int getLength() {
        return length;
    }

    public String getMoves() {
        return moves;
    }

    public String getClocks() {
        return clocks;
    }
}
