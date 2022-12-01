package com.zatec.gotapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.zatec.gotapp.databinding.FragmentLandingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingFragment : Fragment() {

    lateinit var binding: FragmentLandingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLandingBinding.inflate(inflater)
        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment

        binding.bottomNavView.setupWithNavController(navHostFragment.findNavController())

        binding.bottomNavView.setOnItemReselectedListener {  }
        return binding.root
    }
}