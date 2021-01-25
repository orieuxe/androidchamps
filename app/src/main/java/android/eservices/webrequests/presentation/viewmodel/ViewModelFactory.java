package android.eservices.webrequests.presentation.viewmodel;

import android.eservices.webrequests.data.repository.tournament.ITournamentRepository;
import android.eservices.webrequests.data.repository.tournament.TournamentRepository;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ITournamentRepository tournamentRepository;

    public ViewModelFactory(ITournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TournamentSelectViewModel.class)) {
            return (T) new TournamentSelectViewModel(tournamentRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}