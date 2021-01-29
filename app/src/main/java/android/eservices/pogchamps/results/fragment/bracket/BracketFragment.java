package android.eservices.pogchamps.results.fragment.bracket;

import android.eservices.pogchamps.R;
import android.eservices.pogchamps.data.api.model.Match;
import android.eservices.pogchamps.data.di.FakeDependencyInjection;
import android.eservices.pogchamps.results.fragment.TournamentFragment;
import android.eservices.pogchamps.results.adapter.MatchAdapter;
import android.eservices.pogchamps.results.viewmodel.MatchViewModel;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class BracketFragment extends TournamentFragment {
    protected static final String[] rounds = {"Quarterfinals", "Semifinals", "Final"};
    private View rootView;
    private Map<String, MatchAdapter> adapters = new HashMap<>();
    protected MatchViewModel matchViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_bracket, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        for (String round: rounds) {
            setupRecyclerView(round);
        }
        registerViewModels();
    }

    private void setupRecyclerView(String round) {
        int id = getResources().getIdentifier("round_"+round.toLowerCase(), "id", Objects.requireNonNull(getActivity()).getPackageName());
        RecyclerView recyclerView = rootView.findViewById(id);
        MatchAdapter adapter = new MatchAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setNestedScrollingEnabled(false);
        adapters.put(round, adapter);
    }

    private void registerViewModels() {
        matchViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(MatchViewModel.class);
        retrieveResults(tournamentId);
    }

    protected void retrieveResults(int tournamentId, String round){
        matchViewModel.getMatchsFrom(tournamentId, round).observe(getViewLifecycleOwner(), matches -> {
            Objects.requireNonNull(adapters.get(rounds[0])).bindViewModels(matches, rounds[0]);

            List<Match> semiMatches = new ArrayList<>();
            List<Match> finalMatch = new ArrayList<>();
            if(matches.size() == 4){
                semiMatches.add(matches.get(0).getNextMatch());
                semiMatches.add(matches.get(2).getNextMatch());
                finalMatch.add(matches.get(0).getNextMatch().getNextMatch());
            }
            Objects.requireNonNull(adapters.get(rounds[1])).bindViewModels(semiMatches, rounds[1]);
            Objects.requireNonNull(adapters.get(rounds[2])).bindViewModels(finalMatch, rounds[2]);
        });
    }
}
