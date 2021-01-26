package android.eservices.pogchamps.data.repository.tournament;

import android.eservices.pogchamps.data.api.model.Tournament;
import android.eservices.pogchamps.data.api.service.TournamentService;

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
