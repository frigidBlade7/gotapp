package com.zatec.gotapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navGraphViewModels
import androidx.paging.LoadState
import com.zatec.features.characters.ui.CharactersListAdapter
import com.zatec.features.houses.ui.HousesListAdapter
import com.zatec.features.houses.viewmodels.HousesViewModel
import com.zatec.gotapp.core.ui.BaseLoadStateAdapter
import com.zatec.gotapp.databinding.FragmentHousesListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HousesListFragment : Fragment() {

    lateinit var binding: FragmentHousesListBinding

    private val viewmodel: HousesViewModel by navGraphViewModels(R.id.dashboard_nav){
        defaultViewModelProviderFactory
    }

    private val adapter = HousesListAdapter {
        Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHousesListBinding.inflate(inflater)

        binding.recyclerView.adapter = adapter.withLoadStateFooter(BaseLoadStateAdapter())

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewmodel.pagedHouses.collectLatest {
                    adapter.submitData(it)
                }
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
            binding.swipeRefreshLayout.isRefreshing = false
        }
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest {
                Timber.d(it.toString())

                when (it.source.refresh) {
                    is LoadState.Error -> {
                        binding.swipeRefreshLayout.isRefreshing = false
                        //binding.shimmer.isVisible = false
                    }

                    is LoadState.Loading -> {
                        //binding.shimmer.isVisible = true
                    }

                    is LoadState.NotLoading -> {
                        //binding.shimmer.isVisible = false
                        binding.swipeRefreshLayout.isRefreshing = false

                        val data = adapter.snapshot().items

                        binding.emptyState.root.isVisible = data.isEmpty()

                    }
                }
            }
        }
        return binding.root
    }
}