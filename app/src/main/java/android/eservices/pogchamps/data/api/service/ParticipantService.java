package android.eservices.pogchamps.data.api.service;

import android.eservices.pogchamps.data.api.model.Participant;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ParticipantService {
    /**
     * Récupérer tous les participants d'un tournoi.
     * @param tournamentId id du tournoi
     * @return la liste des participants
     */
    @GET("participant/{tournamentId}/all")
    Single<List<Participant>> getAllParticipants(@Path("tournamentId") int tournamentId);


    /**
     * Récupérer tous les participants d'un groupe d'un tournoi.
     * @param tournamentId id du tournoi
     * @param group numéro du groupe : A,B,C ou D
     * @return la liste des participants
     */
    @GET("participant/{tournamentId}/from/{group}")
    Single<List<Participant>> getParticipantsFrom(@Path("tournamentId") int tournamentId, @Path("group") Character group);
}
