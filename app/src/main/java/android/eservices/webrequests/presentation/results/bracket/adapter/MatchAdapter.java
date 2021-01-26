package android.eservices.webrequests.presentation.results.bracket.adapter;

import android.eservices.webrequests.R;
import android.eservices.webrequests.data.api.model.Match;
import android.eservices.webrequests.data.api.model.Player;
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

import io.reactivex.annotations.NonNull;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {


    public static class MatchViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView resultTextView;
        private TextView dateTextView;
        private ImageView icon1ImageView;
        private ImageView icon2ImageView;
        private View v;

        public MatchViewHolder(View v) {
            super(v);
            this.v = v;
            titleTextView = v.findViewById(R.id.match_title);
            resultTextView = v.findViewById(R.id.match_result);
            dateTextView = v.findViewById(R.id.match_date);
            icon1ImageView = v.findViewById(R.id.player1_icon);
            icon2ImageView = v.findViewById(R.id.player1_icon);
            setupListeners();
        }

        private void setupListeners() {

        }

        void bind(Match match) {

            Player p1 = match.getParticipant1().getPlayer();
            Player p2 = match.getParticipant2().getPlayer();
            titleTextView.setText(String.format("%s vs %s", p1.getTwitch(), p2.getTwitch()));
            resultTextView.setText(match.getResult());
            dateTextView.setText((new SimpleDateFormat("dd MM yyyy")).format(match.getDate()));
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

    }

    private List<Match> matchList;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MatchAdapter() {
        matchList = new ArrayList<>();
    }

    public void bindViewModels(List<Match> matchList) {
        this.matchList.clear();
        this.matchList.addAll(matchList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match, parent, false);
        return new MatchViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MatchViewHolder holder, int position) {
        holder.bind(matchList.get(position));
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return matchList.size();
    }


}