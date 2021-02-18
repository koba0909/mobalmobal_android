package com.mashup.mobalmobal.ui.donationdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.funin.base.funinbase.base.BaseViewModelFragment
import com.mashup.mobalmobal.R
import com.mashup.mobalmobal.data.vo.Donation
import dagger.hilt.android.AndroidEntryPoint

/**
 * 도네 상세페이지
 */
@AndroidEntryPoint
class DonationDetailFragment : BaseViewModelFragment() {
    companion object {
        private const val TAG = "DetailFragment"
        private const val KEY_SELECTED_DONATION = "key_selected_donation"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onBindViewModels() {
        super.onBindViewModels()
        initFromArguments()
    }

    private fun initFromArguments() = arguments?.let {
        val donation = it.getSerializable(KEY_SELECTED_DONATION) as? Donation
    }

    private fun goDetailToDonate() =
        findNavController().navigate(R.id.action_detailFragment_to_donateFragment)
}