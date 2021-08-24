package com.example.alugacar.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alugacar.db.MotoristaEntity
import com.example.alugacar.repository.MotoristaRepository
import kotlinx.coroutines.launch

class MotoristaListViewModel(
    private val repository: MotoristaRepository
) : ViewModel() {
    private val _allMotoristasEvent = MutableLiveData<List<MotoristaEntity>>()
    val allMotoristasEvent : LiveData<List<MotoristaEntity>>
        get() = _allMotoristasEvent

    fun getMotoristas() = viewModelScope.launch {
        _allMotoristasEvent.postValue(repository.getAllMotoristas())
    }
}