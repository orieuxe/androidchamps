package android.eservices.webrequests.presentation.viewmodel;

import android.eservices.webrequests.data.api.model.Tournament;

public class TournamentViewModel {
    private int id;
    private PlayerViewModel winner;

    public TournamentViewModel(Tournament tournament) {
        this.id = tournament.getId();
        this.winner = tournament.getWinner() != null ? new PlayerViewModel(tournament.getWinner()) : null;
    }

    public int getId() {
        return id;
    }

    public PlayerViewModel getWinner() {
        return winner;
    }
}
