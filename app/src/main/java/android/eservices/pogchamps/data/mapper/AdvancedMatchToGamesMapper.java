package android.eservices.pogchamps.data.mapper;

import android.eservices.pogchamps.data.api.model.AdvancedMatch;
import android.eservices.pogchamps.data.api.model.Game;

import java.util.List;

public class AdvancedMatchToGamesMapper {
    public static List<Game> map(AdvancedMatch match){
        return match.getGames();
    }
}
