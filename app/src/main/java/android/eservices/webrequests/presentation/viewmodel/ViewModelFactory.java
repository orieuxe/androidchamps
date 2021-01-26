package android.eservices.webrequests.presentation.viewmodel;

import android.eservices.webrequests.data.repository.participant.IParticipantRepository;
import android.eservices.webrequests.data.repository.tournament.ITournamentRepository;
import android.eservices.webrequests.data.repository.tournament.TournamentRepository;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ITournamentRepository tournamentRepository;
    private final IParticipantRepository participantRepository;

    public ViewModelFactory(ITournamentRepository tournamentRepository, IParticipantRepository participantRepository) {
        this.tournamentRepository = tournamentRepository;
        this.participantRepository = participantRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TournamentSelectViewModel.class)) {
            return (T) new TournamentSelectViewModel(tournamentRepository);
        }
        if (modelClass.isAssignableFrom(ParticipantViewModel.class)) {
            return (T) new ParticipantViewModel(participantRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}