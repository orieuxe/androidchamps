package android.eservices.pogchamps.data.repository.tournament;

import android.eservices.pogchamps.data.api.model.Tournament;

import java.util.List;

import io.reactivex.Single;

public interface ITournamentRepository {
    Single<List<Tournament>> getAllTournaments();
}
