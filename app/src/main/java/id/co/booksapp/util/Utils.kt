  package id.co.booksapp.util

import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior

fun ViewGroup.sheetBehavior(): BottomSheetBehavior<*> {
    return BottomSheetBehavior.from(this)
}
