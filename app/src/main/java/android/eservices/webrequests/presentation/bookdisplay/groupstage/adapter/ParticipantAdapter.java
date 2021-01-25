package android.eservices.webrequests.presentation.bookdisplay.groupstage.adapter;

import android.eservices.webrequests.R;
import android.eservices.webrequests.presentation.viewmodel.ParticipantViewModel;
import android.eservices.webrequests.presentation.viewmodel.PlayerViewModel;
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

public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.ParticipantViewHolder> {


    public static class ParticipantViewHolder extends RecyclerView.ViewHolder {
        private TextView twitchTextView;
        private TextView usernameTextView;
        private ImageView iconImageView;
        private View v;

        public ParticipantViewHolder(View v, final ParticipantInterface participantInterface) {
            super(v);
            this.v = v;
            twitchTextView = v.findViewById(R.id.player_twitch);
            usernameTextView = v.findViewById(R.id.player_username);
            iconImageView = v.findViewById(R.id.player_icon);
            setupListeners();
        }

        private void setupListeners() {

        }

        void bind(ParticipantViewModel participantViewModel) {
            PlayerViewModel p = participantViewModel.getPlayer();
            twitchTextView.setText(p.getTwitch());
            usernameTextView.setText(p.getUsername());
            Glide.with(v)
                    .load(p.getIconUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(iconImageView);

        }

    }

    private List<ParticipantViewModel> participantViewModelList;
    private ParticipantInterface participantInterface;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ParticipantAdapter(ParticipantInterface participantInterface) {
        participantViewModelList = new ArrayList<>();
        this.participantInterface = participantInterface;
    }

    public void bindViewModels(List<ParticipantViewModel> participantViewModelList) {
        this.participantViewModelList.clear();
        this.participantViewModelList.addAll(participantViewModelList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ParticipantViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_match, parent, false);
        return new ParticipantViewHolder(v, participantInterface);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ParticipantViewHolder holder, int position) {
        holder.bind(participantViewModelList.get(position));
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return participantViewModelList.size();
    }


}