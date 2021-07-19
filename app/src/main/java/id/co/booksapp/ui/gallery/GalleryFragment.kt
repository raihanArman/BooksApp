package id.co.cobadrawer.ui.gallery

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import id.co.booksapp.ui.MainActivity
import id.co.booksapp.R
import id.co.booksapp.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private lateinit var dataBinding : FragmentGalleryBinding

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).drawerLayout.closeDrawer(Gravity.RIGHT)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false)
        return dataBinding.root
    }

}