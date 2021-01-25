package android.eservices.webrequests.data.repository.tournament;

import android.eservices.webrequests.data.api.model.Tournament;
import android.eservices.webrequests.data.api.service.TournamentService;

import java.util.List;

import io.reactivex.Single;

public class TournamentRepository implements ITournamentRepository {

    private TournamentService tournamentService;

    public TournamentRepository(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    public Single<List<Tournament>> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }
}
