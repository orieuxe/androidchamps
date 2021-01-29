package android.eservices.pogchamps.results.adapter;

import android.content.Context;
import android.content.Intent;
import android.eservices.pogchamps.R;
import android.eservices.pogchamps.data.api.model.Match;
import android.eservices.pogchamps.data.api.model.Player;
import android.eservices.pogchamps.results.MatchActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.annotations.NonNull;

public class MatchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "poggers";

    public static class MatchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private static final String MATCH = "match";
        private TextView titleTextView;
        private TextView resultTextView;
        private TextView dateTextView;
        private TextView contextTextView;
        private ImageView icon1ImageView;
        private ImageView icon2ImageView;
        private View v;
        private Match match;

        public MatchViewHolder(View v) {
            super(v);
            this.v = v;
            v.setOnClickListener(this);
            titleTextView = v.findViewById(R.id.match_title);
            resultTextView = v.findViewById(R.id.match_result);
            dateTextView = v.findViewById(R.id.match_date);
            contextTextView = v.findViewById(R.id.match_context);
            icon1ImageView = v.findViewById(R.id.player1_icon);
            icon2ImageView = v.findViewById(R.id.player2_icon);
        }

        void bind(Match match) {
            this.match = match;
            Player p1 = match.getParticipant1().getPlayer();
            Player p2 = match.getParticipant2().getPlayer();
            titleTextView.setText(String.format("%s vs %s", p1.getTwitch(), p2.getTwitch()));
            resultTextView.setText(match.getResult());
            dateTextView.setText((new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH)).format(match.getDate()));
            if(match.getStage().equals("group")){
                contextTextView.setText(("Round " + match.getRound()));
            }else{
                contextTextView.setText(String.format("%s %s", match.getRound(), match.getStage()));
            }
            Glide.with(v)
                    .load(p1.getIconUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(icon1ImageView);

            Glide.with(v)
                    .load(p2.getIconUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(icon2ImageView);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: "+match.getId());
            Context context = view.getContext();
            Intent intent = new Intent(context, MatchActivity.class);
            intent.putExtra(MATCH, match);
            context.startActivity(intent);
        }
    }

    private static class RoundViewHolder extends RecyclerView.ViewHolder {
        private TextView roundTextView;
        public RoundViewHolder(View v) {
            super(v);
            this.roundTextView = v.findViewById(R.id.header_label);
        }

        void bind(String round) {
            roundTextView.setText(round);
        }
    }

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_MATCH = 1;
    private List<Match> matchList;
    private String round; 
    
    public MatchAdapter() {
        matchList = new ArrayList<>();
        round = "";
    }

    public void bindViewModels(List<Match> matchList, String round) {
        this.matchList.clear();
        this.matchList.addAll(matchList);
        this.round = round;
        notifyDataSetChanged();
    }

    public void clearViewModels(){
        this.matchList.clear();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;

        return TYPE_MATCH;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
            return new RoundViewHolder(v);
        }
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match, parent, false);
        return new MatchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MatchViewHolder){
            ((MatchViewHolder) holder).bind(matchList.get(position - 1));
        }else if(holder instanceof RoundViewHolder){
            ((RoundViewHolder) holder).bind(round);
        }
    }

    @Override
    public int getItemCount() {
        return matchList.size() + 1;
    }
}