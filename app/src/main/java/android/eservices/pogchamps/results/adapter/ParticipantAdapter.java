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

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class ParticipantAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "poggers";

    public static class ParticipantViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener  {
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


        void bind(Participant participant) {
            this.participant = participant;
            Player p = participant.getPlayer();
            twitchTextView.setText(p.getTwitch());
            usernameTextView.setText(p.getUsername());
            pointsTextView.setText(String.format("%s points", participant.getPoints()));
            playedTextView.setText(String.format("played %s", participant.getPlayed()));
            Glide.with(v)
                    .load(p.getIconUrl())
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

    private static class GroupViewHolder extends RecyclerView.ViewHolder {
        private TextView groupTextView;
        public GroupViewHolder(View v) {
            super(v);
            this.groupTextView = v.findViewById(R.id.header_label);
        }

        void bind(Character group) {
            groupTextView.setText(String.format("group %s", group.toString()));
        }
    }

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_PARTICIPANT = 1;
    private List<Participant> participantList;
    private Character group;

    public ParticipantAdapter() {
        participantList = new ArrayList<>();
        this.group = ' ';
    }

    public void bindViewModels(List<Participant> participantList, Character group) {
        this.participantList.clear();
        this.participantList.addAll(participantList);
        this.group = group;
        notifyDataSetChanged();
    }

    public void clearViewModels(){
        this.participantList.clear();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;

        return TYPE_PARTICIPANT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        if(viewType == TYPE_HEADER){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
            return new GroupViewHolder(v);
        }
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player, parent, false);
        return new ParticipantViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ParticipantViewHolder){
            ((ParticipantViewHolder) holder).bind(participantList.get(position - 1));
        }else if(holder instanceof GroupViewHolder){
            ((GroupViewHolder) holder).bind(group);
        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return participantList.size() + 1;
    }
}