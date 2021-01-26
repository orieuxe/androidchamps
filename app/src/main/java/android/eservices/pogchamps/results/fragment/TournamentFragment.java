package android.eservices.pogchamps.results.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class TournamentFragment extends Fragment {
    private static final String TOURNAMENT_ID = "tournamentId";
    protected int tournamentId;

    public abstract void retrieveResults(int tournamentId);

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.tournamentId = getArguments().getInt(TOURNAMENT_ID);
        return null;
    }
}
