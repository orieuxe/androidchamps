package android.eservices.pogchamps.results;

import android.eservices.pogchamps.R;
import android.eservices.pogchamps.data.api.model.Match;
import android.eservices.pogchamps.data.api.model.Player;
import android.eservices.pogchamps.results.adapter.GameAdapter;
import android.eservices.pogchamps.results.adapter.MatchAdapter;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MatchActivity extends BaseActivity {
    private static final String MATCH = "match";
    private GameAdapter adapter;
    private Match match;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        match = (Match) getIntent().getSerializableExtra(MATCH);
        updateToolBar();
        setupRecyclerView();
    }

    private void updateToolBar(){
        super.updateToolBar(match.getTournament().getId());
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.match_games);
        adapter = new GameAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);

        Player p1 = match.getParticipant1().getPlayer();
        Player p2 = match.getParticipant2().getPlayer();
        adapter.bindViewModels(match.getGames(), String.format("%s %s %s", p1.getTwitch(), match.getResult(), p2.getTwitch()));
    }
}