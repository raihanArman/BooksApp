package id.co.booksapp

import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior

fun ViewGroup.sheetBehavior(): BottomSheetBehavior<*> {
    return BottomSheetBehavior.from(this)
}
