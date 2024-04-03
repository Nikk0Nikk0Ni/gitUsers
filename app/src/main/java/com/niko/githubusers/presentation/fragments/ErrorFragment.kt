package com.niko.githubusers.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.niko.githubusers.R
import com.niko.githubusers.databinding.FragmentErrorBinding
import com.niko.githubusers.databinding.FragmentUsersBinding

class ErrorFragment : Fragment(R.layout.fragment_error) {
    private var _binding: FragmentErrorBinding? = null
    private val binding: FragmentErrorBinding
        get() = _binding ?: throw RuntimeException("Fragment error == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentErrorBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRetry.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}