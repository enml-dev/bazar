package com.enml.bazar.utils;

import android.widget.ImageView;

import com.koushikdutta.ion.Ion;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.AlbumLoader;

public class MediaLoader implements AlbumLoader {

    @Override
    public void load(ImageView imageView, AlbumFile albumFile) {
        load(imageView, albumFile.getPath());
    }

    @Override
    public void load(ImageView imageView, String url) {
        Ion.with(imageView.getContext()).load(url).intoImageView(imageView);
        /*Glide.with(imageView.getContext())
                .load(url)
                //.error(R.drawable.placeholder)
                //.placeholder(R.drawable.placeholder)
                //.crossFade()
                .into(imageView);*/
    }
}