package android.eservices.webrequests.presentation.viewmodel;

import android.eservices.webrequests.data.api.model.Game;
import android.eservices.webrequests.data.api.model.Match;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MatchViewModel {
    private int id;
    private ParticipantViewModel participant1;
    private ParticipantViewModel participant2;
    private List<GameViewModel> games;
    private String result;
    private String round;
    private Date date;
    private String stage;
    private TournamentViewModel tournament;

    public MatchViewModel(Match match) {
        this.id  = match.getId();
        this.participant1 = new ParticipantViewModel(match.getParticipant1());
        this.participant2 = new ParticipantViewModel(match.getParticipant2());
        this.games = new ArrayList<>();
        for (Game game : match.getGames()) {
            this.games.add(new GameViewModel(game));
        }
        this.result = match.getResult();
        this.round = match.getRound();
        this.date = match.getDate();
        this.stage = match.getStage();
        this.tournament = new TournamentViewModel(match.getTournament());
    }

    public int getId() {
        return id;
    }

    public ParticipantViewModel getParticipant1() {
        return participant1;
    }

    public ParticipantViewModel getParticipant2() {
        return participant2;
    }

    public List<GameViewModel> getGames() {
        return games;
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

    public TournamentViewModel getTournament() {
        return tournament;
    }
}
