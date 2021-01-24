package android.eservices.webrequests.presentation.bookdisplay.search.adapter;

import android.eservices.webrequests.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {


    public static class BookViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView authorsTextView;
        private ImageView iconImageView;
        private View v;
        private BookItemViewModel bookItemViewModel;
        private BookActionInterface bookActionInterface;
        private Switch favoriteSwitch;

        public BookViewHolder(View v, final BookActionInterface bookActionInterface) {
            super(v);
            this.v = v;
            titleTextView = v.findViewById(R.id.book_title_textview);
            authorsTextView = v.findViewById(R.id.book_authors_textview);
            iconImageView = v.findViewById(R.id.book_icon_imageview);
            favoriteSwitch = v.findViewById(R.id.favorite_switch);
            this.bookActionInterface = bookActionInterface;
            setupListeners();
        }

        private void setupListeners() {
            favoriteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    bookActionInterface.onFavoriteToggle(bookItemViewModel.getBookId(), b);
                }
            });
        }

        void bind(BookItemViewModel bookItemViewModel) {
            this.bookItemViewModel = bookItemViewModel;
            titleTextView.setText(bookItemViewModel.getBookTitle());
            authorsTextView.setText(bookItemViewModel.getBookAuthors());
            favoriteSwitch.setChecked(bookItemViewModel.isFavorite());
            Glide.with(v)
                    .load(bookItemViewModel.getIconUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(iconImageView);

        }

    }

    private List<BookItemViewModel> bookItemViewModelList;
    private BookActionInterface bookActionInterface;

    // Provide a suitable constructor (depends on the kind of dataset)
    public BookAdapter(BookActionInterface bookActionInterface) {
        bookItemViewModelList = new ArrayList<>();
        this.bookActionInterface = bookActionInterface;
    }

    public void bindViewModels(List<BookItemViewModel> bookItemViewModelList) {
        this.bookItemViewModelList.clear();
        this.bookItemViewModelList.addAll(bookItemViewModelList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        BookViewHolder bookViewHolder = new BookViewHolder(v, bookActionInterface);
        return bookViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        holder.bind(bookItemViewModelList.get(position));
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return bookItemViewModelList.size();
    }


}