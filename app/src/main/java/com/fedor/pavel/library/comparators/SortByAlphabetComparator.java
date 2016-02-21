package com.fedor.pavel.library.comparators;


import com.fedor.pavel.library.models.BookModel;

import java.util.Comparator;

public class SortByAlphabetComparator implements Comparator<BookModel> {


    @Override
    public int compare(BookModel lhs, BookModel rhs) {

        return String.CASE_INSENSITIVE_ORDER.compare(lhs.getTitle(), rhs.getTitle());

    }
}
