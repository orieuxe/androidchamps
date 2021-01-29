package android.eservices.pogchamps.data.repository.match;

import android.eservices.pogchamps.data.api.model.AdvancedMatch;
import android.eservices.pogchamps.data.api.model.Match;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Path;

public interface IMatchRepository {
    Single<List<Match>> getMatchsFromStage(int tournamentId, String stage);

    Single<List<Match>> getMatchsOf(int participantId);

    Single<AdvancedMatch> getMatch(int matchId);
}
