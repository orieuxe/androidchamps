package android.eservices.webrequests.data.api.service;

import android.eservices.webrequests.data.api.model.Tournament;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface TournamentService {
    @GET("tournament/all")
    Single<List<Tournament>> getAllTournaments();
}
