package com.haur.weaponslist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haur.weaponslist.domain.models.WeaponPreview
import com.haur.weaponslist.domain.repositories.WeaponsRepository
import kotlinx.coroutines.launch

class WeaponsViewModel(
    private val weaponsRepository: WeaponsRepository
): ViewModel() {

    private val _uiState = MutableLiveData<UIState>()

    val uiState = _uiState as LiveData<UIState>

    init {
        _uiState.postValue(
            UIState(isLoading = true, weapons = emptyList())
        )
    }

    fun fetchWeapons(){
        viewModelScope.launch {
            val weapons = weaponsRepository.getWeaponList()
            _uiState.postValue(
                UIState(isLoading = false, weapons= weapons)
            )
        }
    }

    data class UIState(
        val isLoading: Boolean,
        val weapons: List<WeaponPreview>
    )
}