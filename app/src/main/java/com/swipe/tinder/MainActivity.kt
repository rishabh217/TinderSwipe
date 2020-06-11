package com.swipe.tinder

import android.net.Uri
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.widget.Scroller
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Field


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = NonSwipeViewPager(this)

        image_view.addView(viewPager)

        val images = listOf(
                Uri.parse("android.resource://$packageName/${R.drawable.rishabh1}"),
                Uri.parse("android.resource://$packageName/${R.drawable.rishabh2}"),
                Uri.parse("android.resource://$packageName/${R.drawable.rishabh3}"),
                Uri.parse("android.resource://$packageName/${R.drawable.rishabh4}"),
                Uri.parse("android.resource://$packageName/${R.drawable.rishabh5}"),
                Uri.parse("android.resource://$packageName/${R.drawable.rishabh6}"),
                Uri.parse("android.resource://$packageName/${R.drawable.rishabh7}"),
                Uri.parse("android.resource://$packageName/${R.drawable.rishabh8}")
        )

        button.setOnClickListener {
            viewPager.removeAllViews()
            val adapter = ImagePagerAdapter(this, images)
            viewPager.adapter = adapter
            var mScroller: Field? = null
            mScroller = ViewPager::class.java.getDeclaredField("mScroller")
            mScroller.isAccessible = true
            val scroller = Scroller(this, DecelerateInterpolator())
            mScroller.set(viewPager, scroller)
            viewPager.offscreenPageLimit = 3
            viewPager.setPageTransformer(true, CardStackPageTransformer())
        }

        var gotPosition: Int = -1

        seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                Toast.makeText(this@MainActivity, (viewPager.currentItem + 1).toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
//                var a: Int = 0
//                while ( a < viewPager.currentItem+1) {
//                    viewPager.removeView(image_view)
//                    a++
//                }
                viewPager.currentItem = viewPager.currentItem + 1
//                if (gotPosition == adapter.count) {
//                    image_view.visibility = View.GONE
//                    seekbar.visibility = View.GONE
//                }
                p0!!.progress = 0
            }
        })

        viewPager.addOnPageChangeListener(object : SimpleOnPageChangeListener() {
            override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                gotPosition = position + 1
            }
        })
    }
}
