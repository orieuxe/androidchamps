package android.eservices.pogchamps.results.adapter;

import android.eservices.pogchamps.R;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<MyViewHolder> {

    protected static class HeaderViewHolder extends MyViewHolder {
        private TextView headerTextView;
        public HeaderViewHolder(View v) {
            super(v);
            this.headerTextView = v.findViewById(R.id.header_label);
        }

        @Override
        protected void bind(Object header) {
            headerTextView.setText((String) header);
        }
    }

    protected static final int TYPE_HEADER = 0;
    protected static final int TYPE_ITEM = 1;
    protected String header;
    protected List<T> list;

    public BaseAdapter() {
        this.list = new ArrayList<>();
    }

    public void bindViewModels(List<T> list, String header) {
        this.list.clear();
        if(list.isEmpty()){
            header = "No data for ".concat(header);
        }else{
            this.list.addAll(list);
        }
        this.header = header;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
        return new HeaderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(holder instanceof HeaderViewHolder){
            holder.bind(header);
        }else{
            holder.bind(list.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }
}
