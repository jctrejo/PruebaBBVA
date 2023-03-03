package com.upax.bbvaprueba1.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.upax.bbvaprueba1.common.PokemonUiState
import com.upax.bbvaprueba1.data.datasource.response.FormResponse
import com.upax.bbvaprueba1.data.datasource.response.PokemonsResponse
import com.upax.bbvaprueba1.databinding.FragmentBdetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentBdetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BDetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBdetailBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            backButton.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun init() {
        initUiState()
        getDetailPokemon()
    }

    private fun getDetailPokemon() {
        viewModel.getDetailPokemon(args.idPokemon)
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
                        setupDetail(state.data.forms)
                    }
                    is PokemonUiState.Error -> {
                        Snackbar.make(binding.root, state.error, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    }
                    else -> { }
                }
            }
        }
    }

    private fun setupDetail(results: ArrayList<FormResponse>) {
        with(binding) {
            titlePokemon.text = results[0].name
            Glide.with(root.context)
                .load( results[0].url)
                .into(postImageView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
