package android.eservices.pogchamps.results;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.eservices.pogchamps.R;
import android.eservices.pogchamps.data.api.model.Match;
import android.eservices.pogchamps.data.api.model.Participant;
import android.eservices.pogchamps.data.di.FakeDependencyInjection;
import android.eservices.pogchamps.results.adapter.MatchAdapter;
import android.eservices.pogchamps.results.viewmodel.MatchViewModel;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class ParticipantActivity extends BaseActivity {
    private static final String PARTICIPANT = "participant";
    private MatchViewModel matchViewModel;
    private MatchAdapter adapter;
    private Participant participant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        participant = (Participant) getIntent().getSerializableExtra(PARTICIPANT);
        updateToolBar();
        setupRecyclerView();
        registerViewModels();
    }

    private void updateToolBar(){
        super.updateToolBar(participant.getTournament().getId());
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
        RecyclerView recyclerView = findViewById(R.id.participant_matchs);
        adapter = new MatchAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void registerViewModels() {
        matchViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFactory()).get(MatchViewModel.class);
        matchViewModel.getMatchsOf(participant.getId()).observe(this, new Observer<List<Match>>() {
            @Override
            public void onChanged(final List<Match> matches) {
            adapter.bindViewModels(matches, "All matches from "+participant.getPlayer().getTwitch());
            }
        });
    }
}