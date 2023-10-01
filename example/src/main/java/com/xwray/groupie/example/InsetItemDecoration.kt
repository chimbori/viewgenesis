package com.xwray.groupie.example

import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import com.xwray.groupie.example.decoration.InsetItemDecoration

class InsetItemDecoration(
    @ColorInt backgroundColor: Int,
    @Dimension padding: Int
) : InsetItemDecoration(backgroundColor, padding, INSET_TYPE_KEY, INSET)
