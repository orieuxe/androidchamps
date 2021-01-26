package android.eservices.pogchamps.data.repository.match;

import android.eservices.pogchamps.data.api.model.Match;

import java.util.List;

import io.reactivex.Single;

public interface IMatchRepository {
    Single<List<Match>> getMatchsFromStage(int tournamentId, String stage);
}
