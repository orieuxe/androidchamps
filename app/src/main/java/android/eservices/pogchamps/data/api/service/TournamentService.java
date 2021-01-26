package android.eservices.pogchamps.data.api.service;

import android.eservices.pogchamps.data.api.model.Tournament;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface TournamentService {
    @GET("tournament/all")
    Single<List<Tournament>> getAllTournaments();
}
