package android.eservices.webrequests.data.repository.participant;

import android.eservices.webrequests.data.api.model.Participant;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface IParticipantRepository {

    Single<List<Participant>> getAllParticipants(int tournamentId);

    Single<List<Participant>> getParticipantsFrom(int tournamentId, Character group);
}
