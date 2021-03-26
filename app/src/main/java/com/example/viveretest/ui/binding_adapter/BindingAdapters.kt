package com.example.movieapp.ui.binding_adapter

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["viewVisible", "viewInverse"], requireAll = false)
fun setViewVisible(view: View, visible: Boolean?, inverse: Boolean = false) {
    visible?.let {
        view.isVisible = if (inverse) !it else visible
    }
}
