package com.mashup.mobalmobal.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.funin.base.funinbase.base.BaseViewModelFragment
import com.mashup.mobalmobal.R
import com.mashup.mobalmobal.data.vo.Donation
import com.mashup.mobalmobal.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseViewModelFragment() {
    companion object {
        private const val TAG = "MainFragment"
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun goMainToDetail(donation: Donation) =
        MainFragmentDirections.actionMainFragmentToDetailFragment(donation)
            .also { action -> findNavController().navigate(action) }

    private fun goMainToProfile() =
        findNavController().navigate(R.id.action_mainFragment_to_profileFragment)

    private fun goMainToRegistDonation() =
        findNavController().navigate(R.id.action_mainFragment_to_registDonationFragment)
}