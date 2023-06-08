package com.example.servicesjebackcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.servicesjebackcompose.apiControl.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val apiService: ApiService = ApiService.geInstance()

    private val _isRegistered = MutableStateFlow(false)
    val isRegistered: StateFlow<Boolean> = _isRegistered.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _isToken = MutableStateFlow("")
    val theToken: StateFlow<String> = _isToken.asStateFlow()

    private val _userId = MutableStateFlow(-1)
    val userId: StateFlow<Int> = _userId.asStateFlow()

    private val _userPhone = MutableStateFlow("")
    val userPhone: StateFlow<String> = _userPhone.asStateFlow()

    fun register(name: String, email: String, password: String, phone: String) {
        viewModelScope.launch {
            try {
                val response = apiService.register(name, email, password, phone)

                if (response.isSuccessful) {
                    val registerResponse = response.body()

                    if (registerResponse?.success == true) {
                        _isRegistered.value = true
                        _isToken.value = registerResponse.data.token
                        _userId.value = registerResponse.data.id
                        _userPhone.value = registerResponse.data.phone
                    } else {
                        _error.value = registerResponse?.message
                    }
                } else {
                    _error.value = response.message()
                }
            } catch (e: Exception) {
                _error.value = "خطأ في السيرفر"
            }
        }
    }
}
