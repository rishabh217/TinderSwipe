package com.swipe.tinder

import android.view.View
import androidx.viewpager.widget.ViewPager


class CardStackPageTransformer : ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        if (position >= 0) {
            page.scaleX = 0.9f - 0.05f * position
            page.scaleY = 0.9f
            page.alpha = 1f - 0.3f * position
            page.translationX = -page.width * position
            page.translationY = -30 * position
        } else {
            page.alpha = 1 + 0.3f * position
            page.scaleX = 0.9f + 0.05f * position
            page.scaleY = 0.9f
            page.translationX = page.width * position
            page.translationY = 30 * position
        }
    }
}