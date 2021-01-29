package android.eservices.pogchamps.results;

import android.content.Context;
import android.content.SharedPreferences;
import android.eservices.pogchamps.R;
import android.eservices.pogchamps.data.api.model.Game;
import android.eservices.pogchamps.data.api.model.Match;
import android.eservices.pogchamps.data.api.model.Player;
import android.eservices.pogchamps.data.di.FakeDependencyInjection;
import android.eservices.pogchamps.results.adapter.GameAdapter;
import android.eservices.pogchamps.results.viewmodel.GameViewModel;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.bhlangonijr.chesslib.move.MoveList;

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

            for (Game game:games) {
                if(getGameFen(game) == null){
                    setGameFen(game);
                }
                game.setFen(getGameFen(game));
            }

            adapter.bindViewModels(games, String.format("%s %s %s", p1.getTwitch(), match.getResult(), p2.getTwitch()));
        });

    }

    private void setGameFen(Game game){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        MoveList list = new MoveList();
        list.loadFromSan(game.getMoves());
        String fen = list.getFen().split(" ")[0];

        editor.putString(String.valueOf(game.getId()), fen);
        editor.apply();

    }

    public String getGameFen(Game game){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(String.valueOf(game.getId()), null);
    }
}