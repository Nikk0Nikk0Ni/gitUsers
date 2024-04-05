package com.niko.githubusers.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.niko.githubusers.R
import com.niko.githubusers.databinding.FragmentUsersBinding
import com.niko.githubusers.presentation.adapters.UserListAdapter
import com.niko.githubusers.presentation.viewModels.UsersViewModel
import com.niko.githubusers.presentation.viewModels.UsersViewModelFactory

class UsersFragment : Fragment(R.layout.fragment_users) {
    private var lastEl = 59
    private val viewModel by lazy {
        ViewModelProvider(this,UsersViewModelFactory(requireActivity().application))[UsersViewModel::class.java]
    }
    private var _binding: FragmentUsersBinding? = null
    private val binding: FragmentUsersBinding
        get() = _binding ?: throw RuntimeException("Fragment users == null")
    private val userAdapter = UserListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.updateUserList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        observeNetworkConnetcion()
        return binding.root
    }

    private fun observeNetworkConnetcion() {
        viewModel.isNetworkAvailable.observe(viewLifecycleOwner){
            if(!it){
                findNavController().navigate(R.id.action_usersFragment_to_errorFragment)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecView()
        observeLoading()
        addSwipeRefreshListenner()
    }

    private fun addSwipeRefreshListenner() = with(binding) {
        swiperRefreshLay.setOnRefreshListener {
            lastEl = 59
            viewModel.resetIsLoading()
            viewModel.updateUserList()
            swiperRefreshLay.isRefreshing = false

        }
    }

    private fun observeLoading() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it)
                binding.layoutLoading.visibility = View.GONE
            else
                binding.layoutLoading.visibility = View.VISIBLE
        }
    }

    private fun initRecView() {
        userAdapter.tap = {
            findNavController().navigate(
                UsersFragmentDirections.actionUsersFragmentToDetailFragment(
                    it.login
                ), navOptions {
                    anim {
                        enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                        popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                        popExit = androidx.navigation.ui.R.anim.nav_default_pop_exit_anim
                    }
                }
            )
        }
        binding.userRecView.adapter = userAdapter
        observeData()
        addPaggination()
    }

    private fun observeData() {
        viewModel.getUsersList().observe(viewLifecycleOwner) {
            userAdapter.submitList(it)
        }
    }

    private fun addPaggination() {
        binding.userRecView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                if (lastVisibleItemPosition == lastEl) {
                    lastEl += 59
                    viewModel.uploadUserList()
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}