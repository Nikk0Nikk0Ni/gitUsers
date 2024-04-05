package com.niko.githubusers.presentation.fragments

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.niko.githubusers.R
import com.niko.githubusers.databinding.FragmentErrorBinding
import com.niko.githubusers.databinding.FragmentUsersBinding


class ErrorFragment : Fragment(R.layout.fragment_error) {
    private var _binding: FragmentErrorBinding? = null
    private val binding: FragmentErrorBinding
        get() = _binding ?: throw RuntimeException("Fragment error == null")

    private var toast : Toast? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentErrorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRetry.setOnClickListener {
            if (isNetworkAvialiable())
                findNavController().navigate(R.id.action_errorFragment_to_usersFragment)
            else {
                toast?.cancel()
                toast = Toast.makeText(requireContext(), ERROR,Toast.LENGTH_SHORT)
                toast?.show()
            }
        }
    }

    private fun isNetworkAvialiable(): Boolean {
        val connectivityManager =
            requireActivity().application.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    companion object{
        const val ERROR = "Error Internet connection"
    }
}