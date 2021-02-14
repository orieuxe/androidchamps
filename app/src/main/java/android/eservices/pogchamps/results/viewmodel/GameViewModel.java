package android.eservices.pogchamps.results.viewmodel;

import android.eservices.pogchamps.data.api.model.AdvancedMatch;
import android.eservices.pogchamps.data.api.model.Game;
import android.eservices.pogchamps.data.api.model.Game;
import android.eservices.pogchamps.data.mapper.AdvancedMatchToGamesMapper;
import android.eservices.pogchamps.data.repository.match.IMatchRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class GameViewModel extends ViewModel {
    private final CompositeDisposable compositeDisposable;
    private IMatchRepository matchRepository;

    public GameViewModel(IMatchRepository matchRepository) {
        this.matchRepository = matchRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    private MutableLiveData<List<Game>> games;
    private final MutableLiveData<Boolean> isDataLoading = new MutableLiveData<>();


    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public MutableLiveData<List<Game>> getGamesFrom(int matchId) {
        isDataLoading.postValue(true);
        games = new MutableLiveData<>();
        compositeDisposable.add(matchRepository.getMatch(matchId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<AdvancedMatch>() {
                    @Override
                    public void onSuccess(@NonNull AdvancedMatch match) {
                        games.setValue(AdvancedMatchToGamesMapper.map(match));
                        isDataLoading.setValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println(e.toString());
                        isDataLoading.setValue(false);
                    }
                }));
        return games;
    }
}
