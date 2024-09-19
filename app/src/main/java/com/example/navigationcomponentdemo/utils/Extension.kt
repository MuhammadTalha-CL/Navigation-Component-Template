package com.example.navigationcomponentdemo.utils

import androidx.navigation.NavOptions
import com.example.navigationcomponentdemo.R

fun NavOptions.Builder.setLeftSlideAnimation(): NavOptions.Builder {
    return this.setEnterAnim(R.anim.slide_in_left)
        .setExitAnim(R.anim.slide_out_right)
        .setPopEnterAnim(R.anim.pop_in_right)
        .setPopExitAnim(R.anim.pop_out_left)
}

fun NavOptions.Builder.setRightSlideAnimation(): NavOptions.Builder {
    return this.setEnterAnim(R.anim.slide_in_right)
        .setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.pop_right_enter)
        .setPopExitAnim(R.anim.pop_left_exit)
}

fun NavOptions.Builder.setBottomSlideAnimation(): NavOptions.Builder {
    return this.setEnterAnim(R.anim.slide_in_bottom)
        .setExitAnim(R.anim.slide_out_top)
        .setPopEnterAnim(R.anim.pop_in_bottom)
        .setPopExitAnim(R.anim.pop_out_top)
}

fun NavOptions.Builder.setFadeAnimation(): NavOptions.Builder {
    return this.setEnterAnim(R.anim.fade_in)
        .setExitAnim(R.anim.fade_out)
        .setPopEnterAnim(R.anim.fade_in)
        .setPopExitAnim(R.anim.fade_out)
}