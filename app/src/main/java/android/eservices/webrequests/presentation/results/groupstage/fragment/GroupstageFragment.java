package android.eservices.webrequests.presentation.results.groupstage.fragment;

import android.eservices.webrequests.R;
import android.eservices.webrequests.data.api.model.Participant;
import android.eservices.webrequests.data.di.FakeDependencyInjection;
import android.eservices.webrequests.presentation.results.TournamentFragment;
import android.eservices.webrequests.presentation.results.groupstage.adapter.ParticipantAdapter;
import android.eservices.webrequests.presentation.viewmodel.ParticipantViewModel;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupstageFragment extends TournamentFragment {

    public static final String TAB_NAME = "Groupstage";
    public static final String TAG = "poggers";
    private static final char[] groups = "ABCD".toCharArray();
    private View rootView;
    private Map<Character, RecyclerView> recyclerViews = new HashMap<>();

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

        for (Character group: groups){
            setupGroupRecyclerView(group);
        }
        registerViewModels();
    }

    private void setupGroupRecyclerView(Character group) {
        int id = getResources().getIdentifier("group_"+group.toString(), "id", getContext().getPackageName());
        RecyclerView recyclerView = rootView.findViewById(id);
        recyclerView.setAdapter(new ParticipantAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViews.put(group, recyclerView);
    }

    private void registerViewModels() {
    ParticipantViewModel participantViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(ParticipantViewModel.class);
        for (Character group: groups){
            participantViewModel.getParticipantsFrom(tournamentId, group).observe(getViewLifecycleOwner(), new Observer<List<Participant>>() {
                @Override
                public void onChanged(List<Participant> participants) {
                    Character group = 'A';
                    if (participants.size() > 0) {
                        group = participants.get(0).getGroupe();
                    }
                    ParticipantAdapter adapter = (ParticipantAdapter) recyclerViews.get(group).getAdapter();
                    adapter.bindViewModels(participants, group);
                }
            });
        };
    }
}
