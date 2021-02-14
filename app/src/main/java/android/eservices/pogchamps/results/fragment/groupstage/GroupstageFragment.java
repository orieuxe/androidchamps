package android.eservices.pogchamps.results.fragment.groupstage;

import android.eservices.pogchamps.R;
import android.eservices.pogchamps.data.di.FakeDependencyInjection;
import android.eservices.pogchamps.results.fragment.TournamentFragment;
import android.eservices.pogchamps.results.adapter.ParticipantAdapter;
import android.eservices.pogchamps.results.viewmodel.ParticipantViewModel;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GroupstageFragment extends TournamentFragment {

    public static final String TAB_NAME = "Groupstage";
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
            setupRecyclerView(group);
        }
        registerViewModels();
    }

    private void setupRecyclerView(Character group) {
        int id = getResources().getIdentifier("group_"+group.toString(), "id", Objects.requireNonNull(getActivity()).getPackageName());
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
        getParticipantsFrom(tournamentId, groups[0]);
    }

    private void getParticipantsFrom(int tournamentId, Character group) {
        participantViewModel.getParticipantsFrom(tournamentId, group).observe(getViewLifecycleOwner(), participants -> {
            Objects.requireNonNull(adapters.get(group)).bindViewModels(participants, "Group "+group);
            if(groups[groups.length - 1] != group){
                getParticipantsFrom(tournamentId, (char) (group + 1));
            }
        });
    }
}
