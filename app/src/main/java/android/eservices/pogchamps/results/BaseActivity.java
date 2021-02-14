package android.eservices.pogchamps.results;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.eservices.pogchamps.R;
import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {

    public static final String TOURNAMENT_ID = "tournamentId";
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void updateToolBar(int tournamentId){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextAppearance(this, R.style.Toolbar_TitleText);
        toolbar.setTitle(getString(R.string.app_name) + ' ' + tournamentId);
        int colorId = getResources().getIdentifier(getString(R.string.app_name)+tournamentId, "color", getPackageName());
        toolbar.setBackgroundColor(getResources().getColor(colorId));
    }
}