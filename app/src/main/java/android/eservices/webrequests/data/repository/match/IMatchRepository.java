package android.eservices.webrequests.data.repository.match;

import android.eservices.webrequests.data.api.model.Match;

import java.util.List;

import io.reactivex.Single;

public interface IMatchRepository {
    Single<List<Match>> getMatchsFromStage(int tournamentId, String stage);
}
