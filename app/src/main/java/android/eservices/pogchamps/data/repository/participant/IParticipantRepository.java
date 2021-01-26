package android.eservices.pogchamps.data.repository.participant;

import android.eservices.pogchamps.data.api.model.Participant;

import java.util.List;

import io.reactivex.Single;

public interface IParticipantRepository {

    Single<List<Participant>> getAllParticipants(int tournamentId);

    Single<List<Participant>> getParticipantsFrom(int tournamentId, Character group);
}
