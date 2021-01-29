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
import java.util.Locale;

import io.reactivex.annotations.NonNull;

public class MatchAdapter extends BaseAdapter<Match> {
    private static final String TAG = "poggers";

    public static class MatchViewHolder extends MyViewHolder implements View.OnClickListener {
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

        @Override
        protected void bind(Object obj) {
            Match match = (Match) obj;
            this.match = match;
            Player p1 = match.getParticipant1().getPlayer();
            Player p2 = match.getParticipant2().getPlayer();
            titleTextView.setText(String.format("%s vs %s", p1.getTwitch(), p2.getTwitch()));
            resultTextView.setText(match.getResult());
            dateTextView.setText((new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH)).format(match.getDate()));
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

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER)
            return super.onCreateViewHolder(parent, viewType);

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match, parent, false);
        return new MatchViewHolder(v);
    }
}