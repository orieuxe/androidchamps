package android.eservices.webrequests.presentation.viewmodel;

import android.eservices.webrequests.data.api.model.Participant;
import android.eservices.webrequests.data.repository.participant.IParticipantRepository;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ParticipantViewModel extends ViewModel {
    private final CompositeDisposable compositeDisposable;
    private IParticipantRepository participantRepository;
    private static final String TAG = "poggers";

    public ParticipantViewModel(IParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    private MutableLiveData<List<Participant>> participants;
    private final MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();


    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public MutableLiveData<List<Participant>> getParticipantsFrom(int tournamentId, Character group) {
        isDataLoading.postValue(true);
        participants = new MutableLiveData<>();
        Log.d(TAG, "getParticipantsFrom: "+tournamentId+' '+group);
        compositeDisposable.add(participantRepository.getParticipantsFrom(tournamentId, group)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Participant>>() {
                    @Override
                    public void onSuccess(@NonNull List<Participant> participantList) {
                        participants.setValue(participantList);
                        isDataLoading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.toString());
                        isDataLoading.setValue(false);
                    }
                }));
        return participants;
    }
}
