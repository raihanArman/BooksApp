package id.co.booksapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import id.co.booksapp.R
import id.co.booksapp.databinding.FragmentDetailBinding
import id.co.booksapp.sheetBehavior


class DetailFragment : Fragment() {


    private lateinit var behavior: BottomSheetBehavior<*>
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBottomsheet()

        Glide.with(this)
            .load("https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1579332582l/50049513._SX318_.jpg")
            .into(binding.ivBook)

    }
    private fun setupBottomsheet(){
        behavior = binding.bottomSheet.sheetBehavior()
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }
}