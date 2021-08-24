package com.example.alugacar.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alugacar.R
import com.example.alugacar.R.*
import com.example.alugacar.db.DB
import com.example.alugacar.db.MotoristaDAO
import com.example.alugacar.extensions.navigateWithAnimations
import com.example.alugacar.repository.DatabaseDataSource
import com.example.alugacar.repository.MotoristaRepository
import kotlinx.android.synthetic.main.motorista_list_fragment.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MotoristaListFragment : Fragment(layout.motorista_list_fragment) {
    private val viewModel: MotoristaListViewModel by viewModels{
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val motoristaDAO: MotoristaDAO = DB.getInstance(requireContext()).motoristaDAO
                val repository: MotoristaRepository = DatabaseDataSource(motoristaDAO)
                return MotoristaListViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelEvents()
        configureViewListeners()
    }

    private fun observeViewModelEvents() {
        viewModel.allMotoristasEvent.observe(viewLifecycleOwner){allMotoristas ->
            val motoristaListAdapter = MotoristaListAdapter(allMotoristas).apply {
                onItemClick = { motorista ->
                    val directions = MotoristaListFragmentDirections
                        .actionMotoristaListFragmentToMotoristaFragment(motorista)
                    findNavController().navigate(directions)
                }
            }
            with(recycler_motoristas) {
                setHasFixedSize(true)
                adapter = motoristaListAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMotoristas()
    }

    private fun configureViewListeners(){
        fabAddMotorista.setOnClickListener{
            findNavController().navigateWithAnimations(R.id.action_motoristaListFragment_to_motoristaFragment)
        }
    }
}