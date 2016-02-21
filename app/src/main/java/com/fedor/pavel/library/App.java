package com.fedor.pavel.library;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;


public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        configureImageLoader();

    }

    public void configureImageLoader() {

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .diskCacheFileCount(100)
                .diskCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(8)
                .defaultDisplayImageOptions(defaultDisplayImageOptions())
                .build();

        ImageLoader.getInstance().init(config);

    }

    public static DisplayImageOptions defaultDisplayImageOptions() {

        return new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.books)
                .showImageForEmptyUri(R.drawable.books)
                .showImageOnFail(R.drawable.books)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(true)
                .cacheOnDisk(false)
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();
    }
}
