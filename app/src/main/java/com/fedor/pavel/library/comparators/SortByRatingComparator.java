package com.fedor.pavel.library.comparators;


import com.fedor.pavel.library.models.BookModel;

import java.util.Comparator;

public class SortByRatingComparator implements Comparator<BookModel> {


    @Override
    public int compare(BookModel lhs, BookModel rhs) {

        return (int) -(lhs.getRating()-rhs.getRating());

    }
}
