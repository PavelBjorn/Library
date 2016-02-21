package com.fedor.pavel.library.models;


import java.io.Serializable;

public class BookModel implements Serializable {


    private String coverUri;

    private String title;

    private String description;

    private float rating;

    public static final String COVER_URL_KEY = "coverUri";

    public static final String TILE_KEY = "title";

    public static final String RATING_KEY = "rating";

    public static final String DESCRIPTION_KEY = "description";

    public BookModel() {
    }



    public BookModel(String coverUri, String title, String description) {

        this.coverUri = coverUri;

        this.title = title;

        this.description = description;

    }

    public BookModel(String coverUri, String title, String description, float rating) {
        this.coverUri = coverUri;
        this.title = title;
        this.description = description;
        this.rating = rating;
    }

    public void setCoverUri(String coverUri) {
        this.coverUri = coverUri;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getCoverUri() {
        return coverUri;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public float getRating() {
        return rating;
    }


}
