package com.xwray.groupie.example

import androidx.annotation.ColorInt
import com.xwray.groupie.example.decoration.HeaderItemDecoration

class HeaderItemDecoration(
    @ColorInt background: Int,
    sidePaddingPixels: Int
) : HeaderItemDecoration(background, sidePaddingPixels, R.layout.item_header)
