package android.eservices.pogchamps.results.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class MyViewHolder extends RecyclerView.ViewHolder {
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    protected abstract void bind(Object obj);
}
