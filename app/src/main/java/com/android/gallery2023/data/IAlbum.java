package com.android.gallery2023.data;

/**
 * Created by dnld on 6/28/17.
 */

public interface IAlbum {
    String getName();

    String getPath();

    int getCount();

    Media getCover();
}
