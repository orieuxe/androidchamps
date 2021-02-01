package android.eservices.pogchamps.data.api.service;

import android.eservices.pogchamps.data.api.model.AdvancedMatch;
import android.eservices.pogchamps.data.api.model.Match;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MatchService {
    /**
     * Récupérer l'ensemble des matchs d'une étape d'un tournoi
     * @param tournamentId id du tournoi
     * @param stage étape du tournoi : group, winner ou loser
     * @return la liste des matchs
     */
    @GET("duel/{tournamentId}/stage/{stage}")
    Single<List<Match>> getMatchsFromStage(@Path("tournamentId") int tournamentId, @Path("stage") String stage);


    /**
     * Récupérer l'ensemble des matchs d'un joueur participant à un tournoi spécifique.
     * @param participantId
     * @return la liste des matchs
     */
    @GET("duel/of/{participantId}")
    Single<List<Match>> getMatchsOf(@Path("participantId") int participantId);

    /**
     * Récupérer le detail d'un match, notamment l'ensemble des parties jouées.
     * @param duelId
     * @return le contenu détaillé d'un match
     */
    @GET("duel/{duelId}")
    Single<AdvancedMatch> getMatch(@Path("duelId") int duelId);
}
