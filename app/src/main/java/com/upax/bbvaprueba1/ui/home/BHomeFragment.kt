package com.upax.bbvaprueba1.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.upax.bbvaprueba1.common.PokemonUiState
import com.upax.bbvaprueba1.data.datasource.response.PokemonsResponse
import com.upax.bbvaprueba1.databinding.FragmentBhomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BHomeFragment : Fragment() {

    private var _binding: FragmentBhomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BHomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBhomeBinding.inflate(inflater, container, false)
        init()
        val view = binding.root
        return view
    }

    private fun init() {
        initUiState()
        getAffinity()
    }

    private fun setupCompose(results: ArrayList<PokemonsResponse>) {
        binding.composeView.apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
            )
            setContent {
                MaterialTheme() {
                    SetupCompose(results.toList())
                }
            }
        }
    }

    private fun getAffinity() {
        viewModel.getPokemon()
    }

    private fun initUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is PokemonUiState.Loading -> {
                        println("Loading...")
                    }
                    is PokemonUiState.Success -> {
                        println("Success... ${state.data.results}")
                        setupCompose(state.data.results)
                    }
                    is PokemonUiState.Error -> {
                        Snackbar.make(binding.root, state.error, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    }
                    else -> { }
                }
                //loader.dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}