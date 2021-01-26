package android.eservices.pogchamps.results.fragment.bracket;


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
