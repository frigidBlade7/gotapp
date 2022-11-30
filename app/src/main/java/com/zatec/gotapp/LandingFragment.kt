package com.zatec.gotapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.zatec.gotapp.books.usecase.QueryBooksUseCase
import com.zatec.gotapp.databinding.FragmentLandingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class LandingFragment : Fragment() {

    lateinit var binding: FragmentLandingBinding

    @Inject
    lateinit var booksUseCase: QueryBooksUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            booksUseCase.invoke(1,10).collectLatest {
                it.message?.let {
                    Timber.d(it)
                }

                it.errorMessage?.let {
                    Timber.e(it)
                }

                it.errorCode?.let {
                    Timber.d(getString(it))
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLandingBinding.inflate(inflater)
        return binding.root
    }
}