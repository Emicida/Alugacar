package com.example.alugacar.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.alugacar.R
import com.example.alugacar.db.DB
import com.example.alugacar.db.MotoristaDAO
import com.example.alugacar.extensions.hideKeyboard
import com.example.alugacar.repository.DatabaseDataSource
import com.example.alugacar.repository.MotoristaRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.motorista_fragment.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MotoristaFragment : Fragment(R.layout.motorista_fragment) {

    private val viewModel: MotoristaViewModel by viewModels{
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val motoristaDAO: MotoristaDAO = DB.getInstance(requireContext()).motoristaDAO
                val repository: MotoristaRepository = DatabaseDataSource(motoristaDAO)
                return MotoristaViewModel(repository) as T
            }
        }
    }

    private val args: MotoristaFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.motorista?.let{ motorista ->
            button_subscriber.text = getString(R.string.motorista_button_update)
            input_name.setText(motorista.name)
            input_email.setText(motorista.email)
            input_phone.setText(motorista.phone)
            input_adress.setText(motorista.adress)
            input_describe.setText(motorista.describe)
            button_delete.visibility = View.VISIBLE
        }
        observeEvents()
        setListeners()
    }

    private fun observeEvents() {
        viewModel.motoristaStateEventData.observe(viewLifecycleOwner){motoristaState ->
             when (motoristaState){
                 is MotoristaViewModel.MotoristaState.Inserted,
                 is MotoristaViewModel.MotoristaState.Updated,
                 is MotoristaViewModel.MotoristaState.Deleted ->{
                     clearFields()
                     hideKeyboard()
                     requireView().requestFocus()
                     findNavController().popBackStack()
                 }
             }
        }
        viewModel.messageEventData.observe(viewLifecycleOwner){stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()

        }
    }

    private fun clearFields() {
        input_name.text?.clear()
        input_email.text?.clear()
        input_phone.text?.clear()
        input_adress.text?.clear()
        input_describe.text?.clear()
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity){
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        button_subscriber.setOnClickListener{
            val name = input_name.text.toString()
            val email = input_email.text.toString()
            val phone = input_phone.text.toString()
            val adress = input_adress.text.toString()
            val describe = input_describe.text.toString()
            viewModel.addOrUpdateMotorista(name, email, phone, adress, describe, args.motorista?.id ?: 0)
        }
        button_delete.setOnClickListener{
            viewModel.removeMotorista(args.motorista?.id ?: 0)
        }
    }
}