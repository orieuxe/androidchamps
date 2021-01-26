package android.eservices.pogchamps.results.viewmodel;

import android.eservices.pogchamps.data.api.model.Match;
import android.eservices.pogchamps.data.repository.match.IMatchRepository;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MatchViewModel extends ViewModel {
    private final CompositeDisposable compositeDisposable;
    private IMatchRepository matchRepository;
    private static final String TAG = "poggers";

    public MatchViewModel(IMatchRepository matchRepository) {
        this.matchRepository = matchRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    private MutableLiveData<List<Match>> matchs;
    private final MutableLiveData<Boolean> isDataLoading = new MutableLiveData<>();


    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public MutableLiveData<List<Match>> getMatchsFrom(int tournamentId, String stage) {
        isDataLoading.postValue(true);
        matchs = new MutableLiveData<>();
        Log.d(TAG, "getMatchsFrom: "+tournamentId+' '+stage);
        compositeDisposable.add(matchRepository.getMatchsFromStage(tournamentId, stage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSingleObserver<List<Match>>() {
                @Override
                public void onSuccess(@NonNull List<Match> matchList) {
                    matchs.setValue(matchList);
                    isDataLoading.setValue(false);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    System.out.println(e.toString());
                    isDataLoading.setValue(false);
                }
            }));
        return matchs;
    }
}
