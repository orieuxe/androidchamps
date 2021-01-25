package android.eservices.webrequests.presentation.bookdisplay.bracket.fragment;

import android.eservices.webrequests.R;
import android.eservices.webrequests.presentation.bookdisplay.bracket.adapter.MatchAdapter;
import android.eservices.webrequests.presentation.bookdisplay.bracket.adapter.MatchInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WinnerBracketFragment extends Fragment implements MatchInterface {

    public static final String TAB_NAME = "Winner Bracket";
    private View rootView;
    private RecyclerView recyclerView;
    private MatchAdapter matchAdapter;

    private WinnerBracketFragment() {
    }

    public static WinnerBracketFragment newInstance() {
        return new WinnerBracketFragment();
    }

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
        setupRecyclerView();
        registerViewModels();
    }

    private void registerViewModels() {

    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.bracket);
        matchAdapter = new MatchAdapter(this);
        recyclerView.setAdapter(matchAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
