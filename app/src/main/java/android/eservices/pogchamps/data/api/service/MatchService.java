package android.eservices.pogchamps.data.api.service;

import android.eservices.pogchamps.data.api.model.Match;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MatchService {
    @GET("duel/{tournamentId}/stage/{stage}")
    Single<List<Match>> getMatchsFromStage(@Path("tournamentId") int tournamentId, @Path("stage") String stage);
}
