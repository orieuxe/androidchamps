package android.eservices.pogchamps.data.repository.match;

import android.eservices.pogchamps.data.api.model.AdvancedMatch;
import android.eservices.pogchamps.data.api.model.Match;
import android.eservices.pogchamps.data.api.service.MatchService;

import java.util.List;

import io.reactivex.Single;

public class MatchRepository implements IMatchRepository {

    private MatchService matchService;

    public MatchRepository(MatchService matchService) {
        this.matchService = matchService;
    }

    @Override
    public Single<List<Match>> getMatchsFromStage(int tournamentId, String stage) {
        return matchService.getMatchsFromStage(tournamentId, stage);
    }

    @Override
    public Single<List<Match>> getMatchsOf(int participantId) {
        return matchService.getMatchsOf(participantId);
    }

    @Override
    public Single<AdvancedMatch> getMatch(int matchId) {
        return matchService.getMatch(matchId);
    }
}
