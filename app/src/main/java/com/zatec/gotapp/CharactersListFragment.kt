package com.zatec.gotapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navGraphViewModels
import androidx.paging.LoadState
import com.zatec.features.characters.ui.CharactersListAdapter
import com.zatec.features.characters.viewmodels.CharactersViewModel
import com.zatec.gotapp.core.ui.BaseLoadStateAdapter
import com.zatec.gotapp.databinding.FragmentCharactersListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
/**
 * Characters list fragment
 * Displays a list of characters saved to room from api with room using a mediator
 * binding is the layout databinding file
 * viewmodel contains the paging flow info
 * adapter handles the data to display in the recycler view
 * you can pull down from the top of the list to refresh the data
 * tapping an item shows the character name in a toast
 */
@AndroidEntryPoint
class CharactersListFragment : Fragment() {

    lateinit var binding: FragmentCharactersListBinding

    private val viewmodel: CharactersViewModel by navGraphViewModels(R.id.dashboard_nav){
        defaultViewModelProviderFactory
    }

    private val adapter = CharactersListAdapter {
        Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCharactersListBinding.inflate(inflater)

        binding.recyclerView.adapter = adapter.withLoadStateFooter(BaseLoadStateAdapter())

        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.pagedCharacters.collectLatest {
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
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
        viewmodel.getCharacters()

        return binding.root
    }
}