package com.example.alugacar.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alugacar.R
import com.example.alugacar.repository.MotoristaRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class MotoristaViewModel(
    private val repository: MotoristaRepository
) : ViewModel() {

    private val _motoristaStateEventData = MutableLiveData<MotoristaState>()
    val motoristaStateEventData: LiveData<MotoristaState>
        get() = _motoristaStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    fun addOrUpdateMotorista(name: String, email: String, phone: String, adress: String, describe: String, id: Long = 0){
     if(id > 0){
         updateMotorista(id, name, email, phone, adress, describe)
     } else{
         insertMotorista(name, email, phone, adress, describe)
     }
    }

    private fun updateMotorista(id: Long, name: String, email: String, phone: String, adress: String, describe: String) = viewModelScope.launch {
        try {
            repository.updateMotorista(id, name, email, phone, adress, describe)
            _motoristaStateEventData.value = MotoristaState.Updated
            _messageEventData.value = R.string.update_sucess
        } catch (ex: Exception){
            _messageEventData.value = R.string.update_failure
            Log.e(TAG, ex.toString())
        }
    }

    private fun insertMotorista(name: String, email: String, phone: String, adress: String, describe: String) = viewModelScope.launch {
        try {
            val id = repository.insertMotorista(name, email, phone, adress, describe)
            if(id>0){
                _motoristaStateEventData.value= MotoristaState.Inserted
                _messageEventData.value = R.string.subscriber_success
            }
        } catch (ex: Exception){
            _messageEventData.value = R.string.subscriber_failure
            Log.e(TAG,ex.toString())
        }
    }

    fun removeMotorista(id: Long) = viewModelScope.launch {
        try {
            if(id > 0){
                repository.deleteMotorista(id)
                _motoristaStateEventData.value = MotoristaState.Deleted
                _messageEventData.value = R.string.delete_sucess
            }

        } catch (ex: Exception){
            _messageEventData.value = R.string.delete_error
            Log.e(TAG, ex.toString())
        }
    }

    sealed class MotoristaState {
        object Inserted : MotoristaState()
        object Updated : MotoristaState()
        object Deleted : MotoristaState()
    }

    companion object{
        private val TAG = MotoristaViewModel::class.java.simpleName
    }
}
