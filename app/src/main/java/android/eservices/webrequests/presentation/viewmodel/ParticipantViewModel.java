package android.eservices.webrequests.presentation.viewmodel;

import android.eservices.webrequests.data.api.model.Participant;

public class ParticipantViewModel {
    private int id;
    private TournamentViewModel tournament;
    private int points;
    private int played;
    private String group;
    private PlayerViewModel player;

    public ParticipantViewModel(Participant participant) {
        this.id = participant.getId();
        this.tournament = new TournamentViewModel(participant.getTournament());
        this.points = participant.getPoints();
        this.played = participant.getPlayed();
        this.group = participant.getGroup();
        this.player = new PlayerViewModel(participant.getPlayer());
    }

    public int getId() {
        return id;
    }

    public TournamentViewModel getTournament() {
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

    public PlayerViewModel getPlayer() {
        return player;
    }
}
