package com.upax.bbvaprueba1.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.upax.bbvaprueba1.R
import com.upax.bbvaprueba1.common.PokemonUiState
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun init() {
        initUiState()
        getAffinity()
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
                        //loader.show()
                    }
                    is PokemonUiState.Success -> {
                        println("Success... ${state.data}")
                        //loader.dismiss()
                    }
                    is PokemonUiState.Error -> {
                        println("Error...")
                        //loader.dismiss()
                    }
                    else -> { }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}