package android.eservices.pogchamps.results;

import android.eservices.pogchamps.R;
import android.eservices.pogchamps.data.api.model.Game;
import android.eservices.pogchamps.data.api.model.Match;
import android.eservices.pogchamps.data.api.model.Player;
import android.eservices.pogchamps.data.di.FakeDependencyInjection;
import android.eservices.pogchamps.results.adapter.GameAdapter;
import android.eservices.pogchamps.results.adapter.MatchAdapter;
import android.eservices.pogchamps.results.viewmodel.GameViewModel;
import android.eservices.pogchamps.results.viewmodel.MatchViewModel;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MatchActivity extends BaseActivity {
    private static final String MATCH = "match";
    private GameViewModel gameViewModel;
    private GameAdapter adapter;
    private Match match;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        match = (Match) getIntent().getSerializableExtra(MATCH);
        updateToolBar();
        setupRecyclerView();
        retrieveFullGames();
    }

    private void updateToolBar(){
        super.updateToolBar(match.getParticipant1().getTournament().getId());
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.match_games);
        adapter = new GameAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void retrieveFullGames() {
        gameViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFactory()).get(GameViewModel.class);
        gameViewModel.getGamesFrom(match.getId()).observe(this, games -> {

            Player p1 = match.getParticipant1().getPlayer();
            Player p2 = match.getParticipant2().getPlayer();
            adapter.bindViewModels(games, String.format("%s %s %s", p1.getTwitch(), match.getResult(), p2.getTwitch()));
        });

    }
}