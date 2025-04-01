package com.jordangellatly.countries.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jordangellatly.countries.R
import com.jordangellatly.countries.viewmodel.CountriesUiState
import com.jordangellatly.countries.viewmodel.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CountriesFragment : Fragment() {

    private val countriesViewModel: CountriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_countries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                countriesViewModel.apply {
                    uiState.collect {
                        val progressBar =
                            view.findViewById<ProgressBar>(R.id.store_feed_progress_bar)
                        val errorMessage =
                            view.findViewById<TextView>(R.id.store_feed_error_message)
                        progressBar.gone()
                        recyclerView.gone()
                        errorMessage.gone()
                        when (it) {
                            CountriesUiState.Loading -> {
                                progressBar.visible()
                            }

                            is CountriesUiState.Success -> {
                                recyclerView.visible()
                                recyclerView.adapter = CountriesAdapter(requireContext(), it.countries)
                            }

                            CountriesUiState.Error -> {
                                errorMessage.visible()
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance() = CountriesFragment()
    }
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}
