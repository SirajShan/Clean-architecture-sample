package space.stanton.technicaltest.ext

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*

typealias f<T> = (T) -> Unit

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}