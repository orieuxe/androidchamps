package android.eservices.pogchamps.data.api.model;

import android.eservices.pogchamps.R;

public class DefaultPlayer extends Player {
    @Override
    public String getTwitch() {
        return "?";
    }

    @Override
    public String getUsername() {
        return "unknown";
    }

    @Override
    public Object getIcon(){
        return R.drawable.ic_player;
    }
}
