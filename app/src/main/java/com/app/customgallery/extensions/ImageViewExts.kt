package com.app.customgallery.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

// Extension functions used throughout the app.

/**
 * Load [source] in the [ImageView].
 */
fun ImageView.loadImage(source: String) {
    Glide.with(context)
        .load(source)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

/**
 * Load [source] in the [ImageView], with a low resolution version of image as placeholder.
 */
fun ImageView.loadWithThumbnail(source: String) {
    Glide.with(context)
        .load(source)
        .thumbnail(0.25f)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}
