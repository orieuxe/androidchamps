package android.eservices.pogchamps.data.api.service;

import android.eservices.pogchamps.data.api.model.Participant;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ParticipantService {
    @GET("participant/{tournamentId}/all")
    Single<List<Participant>> getAllParticipants(@Path("tournamentId") int tournamentId);

    @GET("participant/{tournamentId}/from/{group}")
    Single<List<Participant>> getParticipantsFrom(@Path("tournamentId") int tournamentId, @Path("group") Character group);
}
