package android.eservices.pogchamps.results.viewmodel;

import android.eservices.pogchamps.data.api.model.Tournament;
import android.eservices.pogchamps.data.repository.tournament.ITournamentRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class TournamentSelectViewModel extends ViewModel {
    private final CompositeDisposable compositeDisposable;
    private ITournamentRepository tournamentRepository;

    public TournamentSelectViewModel(ITournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    private MutableLiveData<List<Tournament>> tournaments;
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public MutableLiveData<List<Tournament>> getTournaments() {
        isDataLoading.postValue(true);
        if(this.tournaments == null){
            tournaments = new MutableLiveData<>();
            compositeDisposable.clear();
            compositeDisposable.add(tournamentRepository.getAllTournaments()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Tournament>>() {
                    @Override
                    public void onSuccess(@NonNull List<Tournament> tournamentList) {
                        tournaments.setValue(tournamentList);
                        isDataLoading.setValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println(e.toString());
                        isDataLoading.setValue(false);
                    }
                }));
        }
        return tournaments;
    }
}