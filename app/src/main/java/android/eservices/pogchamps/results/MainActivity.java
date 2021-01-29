package android.eservices.pogchamps.results;

import android.content.Context;
import android.content.SharedPreferences;
import android.eservices.pogchamps.R;
import android.eservices.pogchamps.data.api.model.Tournament;
import android.eservices.pogchamps.data.di.FakeDependencyInjection;
import android.eservices.pogchamps.results.fragment.TournamentFragment;
import android.eservices.pogchamps.results.fragment.bracket.LoserBracketFragment;
import android.eservices.pogchamps.results.fragment.groupstage.GroupstageFragment;
import android.eservices.pogchamps.results.fragment.bracket.WinnerBracketFragment;
import android.eservices.pogchamps.results.viewmodel.TournamentSelectViewModel;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class MainActivity extends BaseActivity {

    private ViewPager viewPager;
    private TournamentSelectViewModel tournamentSelectViewModel;
    private List<TournamentFragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateToolBar();
        setupViewPagerAndTabs();
        registerViewModels();
    }

    private void updateToolBar(){
        super.updateToolBar(getCurrentTournamentId());
        setSupportActionBar(toolbar);
    }

    private void setupViewPagerAndTabs() {
        Bundle bundle = new Bundle();
        bundle.putInt(TOURNAMENT_ID, getCurrentTournamentId());

        viewPager = findViewById(R.id.tab_viewpager);

        fragments.add(GroupstageFragment.newInstance());
        fragments.add(WinnerBracketFragment.newInstance());
        fragments.add(LoserBracketFragment.newInstance());
        for(TournamentFragment fragment:fragments) {
            fragment.setArguments(bundle);
        }

        viewPager.setAdapter(new FragmentPagerAdapter(
            getSupportFragmentManager(),
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        ) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return GroupstageFragment.TAB_NAME;
                }
                if(position == 1){
                    return WinnerBracketFragment.TAB_NAME;
                }
                return LoserBracketFragment.TAB_NAME;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
    }

    private void registerViewModels() {
        this.tournamentSelectViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFactory()).get(TournamentSelectViewModel.class);
    }

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        menu.clear();
        this.tournamentSelectViewModel.getTournaments().observe(this ,new Observer<List<Tournament>>() {
            @Override
            public void onChanged(List<Tournament> tournaments) {
                for(Tournament tournament: tournaments){
                    String title = String.valueOf(tournament.getId());
                    if(tournament.getWinner() != null){
                        title = title.concat(" (" + tournament.getWinner().getTwitch() + ")");
                    }
                    menu.add(0, tournament.getId(), Menu.NONE, title);
                }
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() != getCurrentTournamentId())
            onTournamentChange(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void onTournamentChange(int tournamentId) {
        setCurrentTournamentId(tournamentId);
        updateToolBar();
        updateFragments();
    }

    private void updateFragments() {
        for(TournamentFragment fragment:fragments) {
            if(fragment.getView() != null){
                fragment.retrieveResults(getCurrentTournamentId());
            }else {
                assert fragment.getArguments() != null;
                fragment.getArguments().putInt(TOURNAMENT_ID, getCurrentTournamentId());
            }
        }
    }

    private void setCurrentTournamentId(int tournamentId){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(TOURNAMENT_ID, tournamentId);
        editor.apply();
    }

    public int getCurrentTournamentId(){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getInt(TOURNAMENT_ID, 3);
    }
}
