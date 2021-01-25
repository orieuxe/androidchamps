package android.eservices.webrequests.presentation.bookdisplay;

import android.eservices.webrequests.R;
import android.eservices.webrequests.data.di.FakeDependencyInjection;
import android.eservices.webrequests.presentation.bookdisplay.bracket.fragment.LoserBracketFragment;
import android.eservices.webrequests.presentation.bookdisplay.groupstage.fragment.GroupstageFragment;
import android.eservices.webrequests.presentation.viewmodel.TournamentViewModel;
import android.eservices.webrequests.presentation.bookdisplay.bracket.fragment.WinnerBracketFragment;
import android.eservices.webrequests.presentation.viewmodel.TournamentSelectViewModel;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TournamentSelectViewModel tournamentSelectViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupViewPagerAndTabs();

        registerViewModels();
    }

    private void registerViewModels() {
        this.tournamentSelectViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFactory()).get(TournamentSelectViewModel.class);
    }

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        menu.clear();
        this.tournamentSelectViewModel.getTournaments().observe(this ,new Observer<List<TournamentViewModel>>() {
            @Override
            public void onChanged(List<TournamentViewModel> tournamentViewModels) {
                for(TournamentViewModel tournament: tournamentViewModels){
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
        // Handle tournament selection

        return super.onOptionsItemSelected(item);
    }


    private void setupViewPagerAndTabs() {
        viewPager = findViewById(R.id.tab_viewpager);

        final GroupstageFragment groupstageFragment = GroupstageFragment.newInstance();
        final WinnerBracketFragment winnerBracketFragment = WinnerBracketFragment.newInstance();
        final LoserBracketFragment loserBracketFragment = LoserBracketFragment.newInstance();

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return groupstageFragment;
                }
                if(position == 1){
                    return winnerBracketFragment;
                }
                return loserBracketFragment;
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


}
