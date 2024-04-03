package com.niko.githubusers.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.niko.githubusers.R
import com.niko.githubusers.databinding.FragmentDetailBinding
import com.niko.githubusers.databinding.FragmentUsersBinding
import com.niko.githubusers.presentation.viewModels.UserDetailViewModel
import com.niko.githubusers.presentation.viewModels.UsersDetailViewModelFactory
import com.squareup.picasso.Picasso

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding ?: throw RuntimeException("Fragment detail == null")

    private val data: DetailFragmentArgs by navArgs()

    private val viewModel by lazy {
        ViewModelProvider(this,UsersDetailViewModelFactory(requireActivity().application))[UserDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        uploadUser()
        return binding.root
    }

    private fun uploadUser() {
        viewModel.setDetail(data.name)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLoading()

    }

    private fun setUserDetail() = with(binding) {
        viewModel.getDetail().observe(viewLifecycleOwner) {
            Picasso.get().load(it.avatar_url).into(binding.imgIconDet)
            tvUserName.text = it.name ?: "null"
            tvUserEmail.text = String.format(resources.getString(R.string.email_s), it.email)
            tvFollowingCount.text =
                String.format(resources.getString(R.string.following_count_d), it.following)
            tvFollowersCount.text =
                String.format(resources.getString(R.string.followers_count_d), it.followers)
            tvDateOfCreatingAcc.text = String.format(
                resources.getString(R.string.date_of_creating_account_s),
                it.created_at
            )
        }
    }

    private fun observeLoading() {
        viewModel.isEnd.observe(viewLifecycleOwner) {
            if (it) {
                binding.layProgress.visibility = View.GONE
                setUserDetail()
            }
        }
    }
}