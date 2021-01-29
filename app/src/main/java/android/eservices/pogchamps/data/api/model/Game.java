package android.eservices.pogchamps.data.api.model;

import com.github.bhlangonijr.chesslib.move.MoveList;

import java.io.Serializable;
import java.util.Date;

public class Game implements Serializable {
    private int id;
    private Date date;
    private String white;
    private String black;
    private String result;
    private int whiteelo;
    private int blackelo;
    private String timecontrol;
    private String termination;
    private String moves;
    private String fen;

    public int getId() {
        return id;
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

    public String getTermination() {
        return termination;
    }

    public String getMoves() {
        return moves;
    }

    public String getFen() {
        return fen;
    }

    public void setFen(String fen) {
        this.fen = fen;
    }
}
