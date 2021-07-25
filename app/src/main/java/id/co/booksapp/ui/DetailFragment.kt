package id.co.booksapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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

    }
    private fun setupBottomsheet(){
        behavior = binding.bottomSheet.sheetBehavior()
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }
}