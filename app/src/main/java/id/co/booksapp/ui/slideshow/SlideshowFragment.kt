package id.co.cobadrawer.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.co.booksapp.R
import id.co.booksapp.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {


    // This property is only valid between onCreateView and
    // onDestroyView.

    private lateinit var dataBinding: FragmentSlideshowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_slideshow, container, false)
        return dataBinding.root
    }
}