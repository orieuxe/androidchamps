package android.eservices.pogchamps.results.adapter;

import android.eservices.pogchamps.R;
import android.eservices.pogchamps.data.api.model.Game;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveList;
import com.github.bhlangonijr.chesslib.pgn.PgnHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.annotations.NonNull;

public class GameAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class GameViewHolder extends RecyclerView.ViewHolder {
        private static final String GAME = "game";
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

        void bind(Game game){
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

    private static class TitleViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        public TitleViewHolder(View v) {
            super(v);
            this.titleTextView = v.findViewById(R.id.header_label);
        }

        void bind(String title) {
            titleTextView.setText(title);
        }
    }

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_GAME = 1;
    private List<Game> gameList;
    private String title; 
    
    public GameAdapter() {
        gameList = new ArrayList<>();
        title = "";
    }

    public void bindViewModels(List<Game> gameList, String title) {
        this.gameList.clear();
        this.gameList.addAll(gameList);
        this.title = title;
        notifyDataSetChanged();
    }

    public void clearViewModels(){
        this.gameList.clear();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;

        return TYPE_GAME;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
            return new TitleViewHolder(v);
        }
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game, parent, false);
        return new GameViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GameViewHolder){
            ((GameViewHolder) holder).bind(gameList.get(position - 1));
        }else if(holder instanceof TitleViewHolder){
            ((TitleViewHolder) holder).bind(title);
        }
    }

    @Override
    public int getItemCount() {
        return gameList.size() + 1;
    }
}