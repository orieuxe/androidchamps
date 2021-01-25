package android.eservices.webrequests.data.repository.tournament;

import android.eservices.webrequests.data.api.model.Tournament;

import java.util.List;

import io.reactivex.Single;

public interface ITournamentRepository {
    Single<List<Tournament>> getAllTournaments();
}
