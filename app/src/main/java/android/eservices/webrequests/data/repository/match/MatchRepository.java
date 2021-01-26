package android.eservices.webrequests.data.repository.match;

import android.eservices.webrequests.data.api.model.Match;
import android.eservices.webrequests.data.api.service.MatchService;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Path;

public class MatchRepository implements IMatchRepository {

    private MatchService matchService;

    public MatchRepository(MatchService matchService) {
        this.matchService = matchService;
    }

    @Override
    public Single<List<Match>> getMatchsFromStage(int tournamentId, String stage) {
        return matchService.getMatchsFromStage(tournamentId, stage);
    }
}
