package android.eservices.webrequests.presentation.results.bracket.fragment;

import android.eservices.webrequests.R;
import android.eservices.webrequests.presentation.results.bracket.adapter.MatchAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


public class LoserBracketFragment extends BracketFragment {

    public static final String TAB_NAME = "Loser Bracket";

    private LoserBracketFragment() {
    }

    public static LoserBracketFragment newInstance() {
        return new LoserBracketFragment();
    }

    @Override
    public void retrieveResults(int tournamentId) {
        this.retrieveResults(tournamentId, "loser");
    }
}
