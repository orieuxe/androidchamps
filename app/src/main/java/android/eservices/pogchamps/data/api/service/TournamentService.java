package android.eservices.pogchamps.data.api.service;

import android.eservices.pogchamps.data.api.model.Tournament;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface TournamentService {
    /**
     * Récupérer l'ensemble des tournoi pogchamps joués avec le vainqueur respectif si le tournoi est terminé.
     * @return la liste des tournoi
     */
    @GET("tournament/all")
    Single<List<Tournament>> getAllTournaments();
}
