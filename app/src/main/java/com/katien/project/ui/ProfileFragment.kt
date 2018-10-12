package com.katien.project.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.katien.project.R
import com.katien.project.di.Injectable
import com.katien.project.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class ProfileFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var profileViewModel: ProfileViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.main_fragment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        profileViewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(ProfileViewModel::class.java)

        profileViewModel.loadProfile("devkate")

        profileViewModel.profile.observe(this@ProfileFragment, Observer {

            username.text = it.username
            fullname.text = it.fullname
            location.text = it.location
            company.text = it.company

            progressBar.visibility = View.GONE
            profileContent.visibility = View.VISIBLE
        })
    }
}