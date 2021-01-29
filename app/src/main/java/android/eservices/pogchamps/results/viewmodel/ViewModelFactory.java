package android.eservices.pogchamps.results.viewmodel;

import android.eservices.pogchamps.data.api.model.Game;
import android.eservices.pogchamps.data.repository.match.IMatchRepository;
import android.eservices.pogchamps.data.repository.participant.IParticipantRepository;
import android.eservices.pogchamps.data.repository.tournament.ITournamentRepository;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import io.reactivex.annotations.NonNull;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ITournamentRepository tournamentRepository;
    private final IParticipantRepository participantRepository;
    private final IMatchRepository matchRepository;

    public ViewModelFactory(ITournamentRepository tournamentRepository, IParticipantRepository participantRepository, IMatchRepository matchRepository) {
        this.tournamentRepository = tournamentRepository;
        this.participantRepository = participantRepository;
        this.matchRepository = matchRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TournamentSelectViewModel.class)) {
            return (T) new TournamentSelectViewModel(tournamentRepository);
        }
        if (modelClass.isAssignableFrom(ParticipantViewModel.class)) {
            return (T) new ParticipantViewModel(participantRepository);
        }
        if (modelClass.isAssignableFrom(MatchViewModel.class)) {
            return (T) new MatchViewModel(matchRepository);
        }
        if (modelClass.isAssignableFrom(GameViewModel.class)) {
            return (T) new GameViewModel(matchRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}