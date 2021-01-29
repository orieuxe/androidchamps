package android.eservices.pogchamps.results.adapter;

import android.eservices.pogchamps.R;
import android.eservices.pogchamps.data.api.model.Game;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Locale;

import io.reactivex.annotations.NonNull;

public class GameAdapter extends BaseAdapter<Game> {

    private static class GameViewHolder extends MyViewHolder {
        private static final String TAG = "poggers";
        private ImageView boardImageView;
        private TextView titleTextView;
        private TextView dateTextView;
        private TextView timecontrolTextView;
        private TextView terminationTextView;
        private View v;
        private Game game;

        public GameViewHolder(View v) {
            super(v);
            this.v = v;
            boardImageView = v.findViewById(R.id.board);
            titleTextView = v.findViewById(R.id.title);
            dateTextView = v.findViewById(R.id.date);
            timecontrolTextView = v.findViewById(R.id.timecontrol);
            terminationTextView = v.findViewById(R.id.termination);
        }

        @Override
        protected void bind(Object obj) {
            Game game = (Game) obj;
            Log.d(TAG, "final position: " + game.getImgUrl());

            titleTextView.setText(String.format("%s vs %s", game.getWhite(), game.getBlack()));
            dateTextView.setText((new SimpleDateFormat("dd MM yyyy hh:ss", Locale.ENGLISH)).format(game.getDate()));
            timecontrolTextView.setText(game.getTimecontrol());
            terminationTextView.setText(game.getTermination());
            Glide.with(v)
                    .load(game.getImgUrl())
                    .into(boardImageView);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER)
           return super.onCreateViewHolder(parent, viewType);

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game, parent, false);
        return new GameViewHolder(v);
    }
}