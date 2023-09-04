package com.iitism.concetto.ui.profilefragment

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iitism.concetto.R
import com.iitism.concetto.auth.authActivity
import com.iitism.concetto.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_profile, container, false)
        binding=FragmentProfileBinding.bind(view)


        val sharedPref=this.activity?.getSharedPreferences("myPref",Context.MODE_PRIVATE)
        Log.d("sharedPref",sharedPref?.getString("teamName","").toString())
        binding.Email.text=sharedPref?.getString("email","")
        binding.EventsParticipated.text=sharedPref?.getString("playerOrganization","")
        binding.TeamNames.text=sharedPref?.getString("playerOrganization","")
        binding.Mobile.text=sharedPref?.getLong("playerMobile",0).toString()
        binding.Course.text=sharedPref?.getString("playerType","").toString()
        binding.PassingYear.text=sharedPref?.getString("playerType","").toString()
        binding.nameText.text=sharedPref?.getString("playerName","").toString()

        binding.logout.setOnClickListener {
            val edit=sharedPref?.edit()
            edit?.clear()
            edit?.apply()
            startActivity(Intent(this.context,authActivity::class.java))
            this.activity?.finish()

        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)



    }

}