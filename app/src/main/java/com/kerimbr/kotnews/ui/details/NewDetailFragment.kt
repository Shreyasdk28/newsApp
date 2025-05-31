package com.kerimbr.kotnews.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kerimbr.kotnews.databinding.FragmentNewDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewDetailFragment : Fragment() {

    private var _binding: FragmentNewDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 