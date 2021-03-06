package com.mashup.mobalmobal.ui.profile.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.funin.base.funinbase.base.BaseViewBindingFragment
import com.funin.base.funinbase.extension.rx.observeOnMain
import com.funin.base.funinbase.extension.rx.subscribeWithErrorLogger
import com.funin.base.funinbase.extension.showToast
import com.mashup.mobalmobal.R
import com.mashup.mobalmobal.constant.Constants.KEY_USER_ID
import com.mashup.mobalmobal.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : BaseViewBindingFragment<FragmentProfileBinding>(),
    ProfileAdapter.ProfileClickListener {

    private val profileViewModel: ProfileViewModel by viewModels()
    private val userId: String by lazy { arguments?.getString(KEY_USER_ID) ?: "" }

    @Inject
    lateinit var profileAdapter: ProfileAdapter

    init {
        checkVerifyUserId()
    }

    private fun checkVerifyUserId() {
        if (userId.isEmpty()) findNavController().popBackStack()
    }

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false)

    override fun onSetupViews(view: View) {
        super.onSetupViews(view)
        binding.recyclerViewProfile.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = profileAdapter
        }
    }

    override fun onBindViewModels() {
        super.onBindViewModels()
        with(profileViewModel) {
            toastSubject.observeOnMain()
                .subscribeWithErrorLogger { context?.showToast(it, Toast.LENGTH_SHORT) }
                .addToDisposables()

            profileSubject.observeOnMain()
                .subscribeWithErrorLogger { profileAdapter.submitList(it) }
                .addToDisposables()
        }
    }

    override fun onProfileItemClick(view: View, position: Int) {
        when (view.id) {
            R.id.tv_setting -> navigateProfileToSettings()
        }
    }

    private fun navigateProfileToMyDonations() =
        findNavController().navigate(R.id.action_profileFragment_to_myDonationsFragment)

    private fun navigateProfileToSettings() =
        findNavController().navigate(R.id.action_profileFragment_to_settingsFragment)

}