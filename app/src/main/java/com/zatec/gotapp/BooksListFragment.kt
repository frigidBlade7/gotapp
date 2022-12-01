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
import com.zatec.gotapp.books.ui.BooksListAdapter
import com.zatec.gotapp.books.viewmodels.BooksViewModel
import com.zatec.gotapp.core.ui.BaseLoadStateAdapter
import com.zatec.gotapp.databinding.FragmentBooksListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class BooksListFragment : Fragment() {

    lateinit var binding: FragmentBooksListBinding

    private val viewmodel: BooksViewModel by navGraphViewModels(R.id.dashboard_nav){
        defaultViewModelProviderFactory
    }

    private val adapter = BooksListAdapter {
        Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBooksListBinding.inflate(inflater)

        binding.recyclerView.adapter = adapter.withLoadStateFooter(BaseLoadStateAdapter())

        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.pagedBooks.collectLatest {
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

        viewmodel.getBooks()
        return binding.root
    }
}