package com.example.servicesjebackcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.servicesjebackcompose.apiService.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val apiService: ApiService = ApiService.geInstance()
    private val _isRegistered = MutableStateFlow(false)
    private val _error = MutableStateFlow<String?>(null)
    val isRegistered: StateFlow<Boolean> = _isRegistered.asStateFlow()
    private val _isToken = MutableStateFlow("")
    private val _userId = MutableStateFlow(-1)
    val userId: StateFlow<Int> = _userId.asStateFlow()
    private val _userPhone = MutableStateFlow("")

    val userPhone: StateFlow<String> = _userPhone.asStateFlow()

    val theToken: StateFlow<String> = _isToken.asStateFlow()
    val error: StateFlow<String?> = _error.asStateFlow()

    fun register(name: String, email: String, password: String,phone:String) {
        viewModelScope.launch {
            try {
                val response = apiService.register(name, email, password,phone)

                if (response.isSuccessful) {
                    val registerResponse = response.body()

                    if (registerResponse?.success == true) {
                        _isRegistered.emit(true)
                        _isToken.emit(registerResponse.data.token)
                        _userId.emit(registerResponse.data.id)
                        _userPhone.emit(registerResponse.data.phone)
                    } else {
                        _error.emit(registerResponse?.message)
                    }
                } else {
                    _error.emit(response.message())
                }
            } catch (e: Exception) {
                _error.emit("حدث خطأ ما")
            }
        }
    }
}
