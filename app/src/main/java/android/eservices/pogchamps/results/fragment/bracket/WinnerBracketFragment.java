package android.eservices.pogchamps.results.fragment.bracket;

public class WinnerBracketFragment extends BracketFragment {

    public static final String TAB_NAME = "Winner Bracket";

    private WinnerBracketFragment() {
    }

    public static WinnerBracketFragment newInstance() {
        return new WinnerBracketFragment();
    }

    @Override
    public void retrieveResults(int tournamentId) {
        this.retrieveResults(tournamentId, "winner");
    }
}
