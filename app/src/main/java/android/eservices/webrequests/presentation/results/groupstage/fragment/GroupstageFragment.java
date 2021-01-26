package android.eservices.webrequests.presentation.results.groupstage.fragment;

import android.eservices.webrequests.R;
import android.eservices.webrequests.data.api.model.Participant;
import android.eservices.webrequests.data.di.FakeDependencyInjection;
import android.eservices.webrequests.presentation.results.TournamentFragment;
import android.eservices.webrequests.presentation.results.groupstage.adapter.ParticipantAdapter;
import android.eservices.webrequests.presentation.viewmodel.ParticipantViewModel;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupstageFragment extends TournamentFragment {

    public static final String TAB_NAME = "Groupstage";
    public static final String TAG = "poggers";
    private static final char[] groups = "ABCD".toCharArray();
    private View rootView;
    private ParticipantViewModel participantViewModel;
    private Map<Character, ParticipantAdapter> adapters = new HashMap<>();

    private GroupstageFragment() {}

    public static GroupstageFragment newInstance() {
        return new GroupstageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_groupstage, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        for (Character group : groups) {
            setupGroupRecyclerView(group);
        }
        registerViewModels();
    }

    private void setupGroupRecyclerView(Character group) {
        int id = getResources().getIdentifier("group_"+group.toString(), "id", getContext().getPackageName());
        RecyclerView recyclerView = rootView.findViewById(id);
        ParticipantAdapter adapter = new ParticipantAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setNestedScrollingEnabled(false);
        adapters.put(group, adapter);
    }

    private void registerViewModels() {
        participantViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(ParticipantViewModel.class);
        retrieveResults(tournamentId);
    }

    @Override
    public void retrieveResults(int tournamentId) {
        for (Character g: groups){
            adapters.get(g).clearViewModels();
            participantViewModel.getParticipantsFrom(tournamentId, g).observe(getViewLifecycleOwner(), new Observer<List<Participant>>() {
                @Override
                public void onChanged(List<Participant> participants) {
                if (participants.isEmpty()) return;
                Character group = participants.get(0).getGroupe();
                adapters.get(group).bindViewModels(participants, group);
                }
            });
        }
    }
}
