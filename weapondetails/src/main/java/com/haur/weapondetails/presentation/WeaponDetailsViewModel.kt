package com.haur.weapondetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haur.weapondetails.domain.models.WeaponDetails
import com.haur.weapondetails.domain.repositories.WeaponDetailsRepository
import kotlinx.coroutines.launch

class WeaponDetailsViewModel(
    private val weaponDetailsRepository: WeaponDetailsRepository
): ViewModel() {

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> = _uiState

    fun fetchWeaponDetails(weaponUUID: String) {
        _uiState.postValue(UIState.Loading)
        viewModelScope.launch {
            val weaponDetails = weaponDetailsRepository.getWeaponDetails(weaponUUID)
            _uiState.postValue(UIState.Success(weaponDetails))
        }
    }
    sealed class UIState {
        object Loading : UIState()
        data class Success(val weaponDetails: WeaponDetails) : UIState()
        data class Error(val message: String) : UIState()
    }
}