package com.xwray.groupie.example.item

import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.chimbori.viewgenesis.example.R
import com.chimbori.viewgenesis.example.databinding.ItemHeaderBinding
import com.xwray.groupie.viewbinding.BindableItem

open class HeaderItem @JvmOverloads constructor(
    @StringRes private val titleStringResId: Int,
    @StringRes private val subtitleResId: Int = 0,
    @DrawableRes private val iconResId: Int = 0,
    private val onIconClickListener: View.OnClickListener? = null
) : BindableItem<ItemHeaderBinding?>() {

    override fun getLayout(): Int = R.layout.item_header

    override fun initializeViewBinding(view: View): ItemHeaderBinding = ItemHeaderBinding.bind(view)

    override fun bind(viewBinding: ItemHeaderBinding, position: Int) {
        viewBinding.apply {
            title.setText(titleStringResId)
            if (subtitleResId != 0) {
                subtitle.setText(subtitleResId)
            }
            subtitle.visibility = if (subtitleResId != 0) View.VISIBLE else View.GONE

            if (iconResId != 0) {
                icon.setImageResource(iconResId)
                icon.setOnClickListener(onIconClickListener)
            }
            icon.visibility = if (iconResId != 0) View.VISIBLE else View.GONE
        }
    }

}
