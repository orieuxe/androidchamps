package android.eservices.pogchamps.results.adapter;

import android.content.Context;
import android.content.Intent;
import android.eservices.pogchamps.R;
import android.eservices.pogchamps.data.api.model.Participant;
import android.eservices.pogchamps.data.api.model.Player;
import android.eservices.pogchamps.results.ParticipantActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import io.reactivex.annotations.NonNull;

public class ParticipantAdapter extends BaseAdapter<Participant> {

    public static class ParticipantViewHolder extends MyViewHolder  implements View.OnClickListener  {
        private static final String PARTICIPANT = "participant";
        private TextView twitchTextView;
        private TextView usernameTextView;
        private ImageView iconImageView;
        private TextView pointsTextView;
        private TextView playedTextView;
        private View v;
        private Participant participant;
        private static final String TAG = "poggers";

        public ParticipantViewHolder(View v) {
            super(v);
            this.v = v;
            v.setOnClickListener(this);
            twitchTextView = v.findViewById(R.id.player_twitch);
            usernameTextView = v.findViewById(R.id.player_username);
            iconImageView = v.findViewById(R.id.player_icon);
            pointsTextView = v.findViewById(R.id.player_points);
            playedTextView = v.findViewById(R.id.player_played);
        }

        @Override
        protected void bind(Object obj) {
            Participant participant = (Participant) obj;
            this.participant = participant;
            Player p = participant.getPlayer();
            twitchTextView.setText(p.getTwitch());
            usernameTextView.setText(p.getUsername());
            pointsTextView.setText(String.format("%s points", participant.getPoints()));
            playedTextView.setText(String.format("played %s", participant.getPlayed()));
            Glide.with(v)
                    .load(p.getIcon())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(iconImageView);
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, ParticipantActivity.class);
            intent.putExtra(PARTICIPANT, participant);
            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
            return new HeaderViewHolder(v);
        }

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player, parent, false);
        return new ParticipantViewHolder(v);
    }
}