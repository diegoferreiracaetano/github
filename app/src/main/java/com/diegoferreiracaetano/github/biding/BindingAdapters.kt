package com.diegoferreiracaetano.github.biding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.diegoferreiracaetano.domain.utils.NetworkState
import com.diegoferreiracaetano.github.R

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("isVisible")
    fun View.isVisible(boolean: Boolean) {
        visibility = if (boolean) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("setLoad")
    fun SwipeRefreshLayout.setLoad(networkState: NetworkState?) {
        isRefreshing = networkState?.status == NetworkState.LOADING.status
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.setImageUrl(url: String?) {
        if (!url.isNullOrEmpty()) {
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .placeholder(context.getDrawable(R.drawable.ic_account))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(true)
                    .into(this)
        }
    }
}