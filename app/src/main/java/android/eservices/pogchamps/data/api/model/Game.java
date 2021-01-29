package android.eservices.pogchamps.data.api.model;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.MoveList;

import java.io.Serializable;
import java.util.Date;

public class Game implements Serializable {
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

    public String getImgUrl() {
        MoveList list = new MoveList();
        list.loadFromSan(getMoves());
        String fen = list.getFen().split(" ")[0];
        return "http://www.fen-to-image.com/image/"+fen;
    }
}
