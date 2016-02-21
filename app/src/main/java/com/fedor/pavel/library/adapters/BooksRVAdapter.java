package com.fedor.pavel.library.adapters;


import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fedor.pavel.library.BookFullInfoActivity;
import com.fedor.pavel.library.BooksTableActivity;
import com.fedor.pavel.library.R;
import com.fedor.pavel.library.comparators.SortByAlphabetComparator;
import com.fedor.pavel.library.comparators.SortByRatingComparator;
import com.fedor.pavel.library.models.BookModel;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Collections;


import butterknife.Bind;
import butterknife.ButterKnife;

public class BooksRVAdapter extends RecyclerView.Adapter<BooksRVAdapter.BooksItemViewHolder> {


    private ArrayList<BookModel> books = new ArrayList<>();

    private LayoutInflater inflater;

    private Activity activity;

    public static final String LOG_TAG = "BooksRVAdapter";

    public BooksRVAdapter(Activity activity) {

        this.activity = activity;

        inflater = LayoutInflater.from(activity);

    }

    public BooksRVAdapter(Activity activity, ArrayList<BookModel> books) {

        this.activity = activity;

        inflater = LayoutInflater.from(activity);

        this.books.addAll(books);

    }

    public void addAllBooks(ArrayList<BookModel> books) {

        int startPoint = this.books.size() - 1;

        this.books.addAll(books);

        notifyItemRangeInserted(startPoint, books.size());

    }

    public void addBook(BookModel book) {

        this.books.add(book);

    }

    public void clear() {

        books.clear();

        notifyItemRangeRemoved(0, books.size());

    }

    public ArrayList<BookModel> getAllBooks() {

        return books;

    }

    public void sort(@IdRes int sortId) {

        switch (sortId) {

            case R.id.activity_books_table_rbAlphabet:


                Collections.sort(books, new SortByAlphabetComparator());

                notifyItemRangeChanged(0, books.size());

                break;

            case R.id.activity_books_table_rbRating:

                Collections.sort(books, new SortByRatingComparator());

                notifyItemRangeChanged(0, books.size());

                break;

        }


    }

    public BookModel getBook(int position) throws IllegalArgumentException {

        if (position > books.size() - 1 || position < 0) {

            throw new IllegalArgumentException("position must be >= 0 and <= books.size()");

        }

        return books.get(position);

    }

    @Override
    public BooksItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new BooksItemViewHolder(inflater.inflate(R.layout.item_rv_books_table, parent, false));

    }


    @Override
    public void onBindViewHolder(BooksItemViewHolder holder, int position) {

        BookModel book = books.get(position);

        holder.tvTitle.setText(book.getTitle());

        ImageLoader.getInstance().displayImage(book.getCoverUri(), holder.imvBooksCover);

        holder.rbBooks.setRating((float) book.getRating());


    }


    @Override
    public int getItemCount() {

        return books.size();

    }


    public class BooksItemViewHolder extends RecyclerView.ViewHolder implements RatingBar.OnRatingBarChangeListener, View.OnClickListener {

        @Bind(R.id.item_rv_booksTable_imvCover)
        ImageView imvBooksCover;

        @Bind(R.id.item_rv_booksTable_tvTitle)
        TextView tvTitle;

        @Bind(R.id.item_rv_booksTable_rbBooks)
        RatingBar rbBooks;

        @Bind(R.id.click_container)
        RelativeLayout clickContainer;


        public BooksItemViewHolder(View itemView) {

            super(itemView);

            ButterKnife.bind(this, itemView);

            clickContainer.setOnClickListener(this);

        }


        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            books.get(getAdapterPosition()).setRating(rating);

            ratingBar.setRating(rating);

        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(activity, BookFullInfoActivity.class);

            intent.putExtra(BooksTableActivity.BOOK_KEY, books.get(getAdapterPosition()));

            activity.startActivityForResult(intent,getAdapterPosition());

            activity.overridePendingTransition(R.anim.start_activity, R.anim.nothing);

        }
    }

}
