package android.eservices.webrequests.data.repository.participant;

import android.eservices.webrequests.data.api.model.Participant;
import android.eservices.webrequests.data.api.service.ParticipantService;
import android.util.Log;

import java.util.List;

import io.reactivex.Single;

public class ParticipantRepository implements IParticipantRepository{
    private ParticipantService participantService;

    public ParticipantRepository(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @Override
    public Single<List<Participant>> getAllParticipants(int tournamentId) {
        return participantService.getAllParticipants(tournamentId);
    }

    @Override
    public Single<List<Participant>> getParticipantsFrom(int tournamentId, Character group) {
        return participantService.getParticipantsFrom(tournamentId, group);
    }
}
