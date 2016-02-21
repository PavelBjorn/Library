package com.fedor.pavel.library;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.fedor.pavel.library.models.BookModel;
import com.fedor.pavel.library.view.CanSwipeScrollView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;


public class BookFullInfoActivity extends AppCompatActivity {

    @Bind(R.id.container)
    CoordinatorLayout container;

    @Bind(R.id.activity_full_bookInfo_scroll)
    CanSwipeScrollView scrollView;

    @Bind(R.id.activity_full_bookInfo_tvTitle)
    TextView tvTitle;

    @Bind(R.id.activity_full_bookInfo_tvDescription)
    TextView tvDescription;

    @Bind(R.id.activity_full_bookInfo_imvCover)
    ImageView imvCover;

    @Bind(R.id.activity_full_bookInfo_rbBooks)
    RatingBar ratingBar;


    private GestureDetectorCompat gestureDetector;

    private BookModel book;

    public static final String LOG_TAG = "BookFullInfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        book = (BookModel) getIntent().getSerializableExtra(BooksTableActivity.BOOK_KEY);

        setContentView(R.layout.activity_book_full_info);

        ButterKnife.bind(this);

        gestureDetector = new GestureDetectorCompat(this, new SwipeGesturesListener());

        scrollView.setGestureListener(gestureDetector);

        tvTitle.setText(book.getTitle());

        tvDescription.setText(book.getDescription());

        ratingBar.setRating(book.getRating());

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {


                Intent intent = new Intent();

                intent.putExtra("rating",rating);

                setResult(RESULT_OK,intent);

            }
        });

        ImageLoader.getInstance().displayImage(book.getCoverUri(), imvCover);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        this.gestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    public class SwipeGesturesListener extends GestureDetector.SimpleOnGestureListener {


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            Log.d(LOG_TAG, "e1.getX() = " + e1.getX() + " e2.getX() = " + e2.getX());

            float diffX = e1.getX() - Math.abs(e2.getX());

            float diffY = e1.getY() - Math.abs(e2.getY());

            if (Math.abs(diffX) > Math.abs(diffY)) {

                if (e1.getX() > e2.getX()) {

                    finish();

                    overridePendingTransition(R.anim.nothing, R.anim.finish_activity);
                }
            }

            return true;


        }
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        overridePendingTransition(R.anim.nothing, R.anim.finish_activity);


    }
}
