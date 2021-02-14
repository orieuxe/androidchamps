package android.eservices.pogchamps.results.adapter;

import android.content.Context;
import android.eservices.pogchamps.R;
import android.eservices.pogchamps.data.api.model.Game;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Locale;

import io.reactivex.annotations.NonNull;

public class GameAdapter extends BaseAdapter<Game> {

    private static class GameViewHolder extends MyViewHolder {
        private ImageView boardImageView;
        private TextView whitePlayerTextView;
        private TextView blackPlayerTextView;
        private TextView resultTextView;
        private TextView dateTextView;
        private TextView timecontrolTextView;
        private TextView terminationTextView;
        private View v;
        private Game game;

        public GameViewHolder(View v) {
            super(v);
            this.v = v;
            boardImageView = v.findViewById(R.id.board);
            whitePlayerTextView = v.findViewById(R.id.white_player);
            blackPlayerTextView = v.findViewById(R.id.black_player);
            resultTextView = v.findViewById(R.id.result);
            dateTextView = v.findViewById(R.id.date);
            timecontrolTextView = v.findViewById(R.id.timecontrol);
            terminationTextView = v.findViewById(R.id.termination);
        }

        @Override
        protected void bind(Object obj) {
            Game game = (Game) obj;

            whitePlayerTextView.setText(String.format("%s (%s)", game.getWhite(), game.getWhiteelo()));
            blackPlayerTextView.setText(String.format("%s (%s)", game.getBlack(), game.getBlackelo()));
            resultTextView.setText(game.getResult());
            dateTextView.setText((new SimpleDateFormat("MM-dd-yyyy hh:ss", Locale.ENGLISH)).format(game.getDate()));
            timecontrolTextView.setText(game.getTimecontrol());
            terminationTextView.setText(game.getTermination());

            CircularProgressDrawable loader = new CircularProgressDrawable(v.getContext());
            loader.start();

            Glide.with(v)
                    .load("http://www.fen-to-image.com/image/"+game.getFen())
                    .placeholder(loader)
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