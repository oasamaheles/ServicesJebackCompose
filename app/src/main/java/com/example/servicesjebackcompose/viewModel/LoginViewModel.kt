package com.example.servicesjebackcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.servicesjebackcompose.apiControl.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val apiService: ApiService = ApiService.geInstance()
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    private val _isToken = MutableStateFlow("")
    private val _userId = MutableStateFlow(-1)
    val userId: StateFlow<Int> = _userId.asStateFlow()
    val theToken: StateFlow<String> = _isToken.asStateFlow()
    private val _userPhone = MutableStateFlow("")
    val userPhone: StateFlow<String> = _userPhone.asStateFlow()

    val error: StateFlow<String?> = _error.asStateFlow()
    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = apiService.login(email, password)
                if (response.code() == 200) {
                    val loginResponse = response.body()
                    if (loginResponse?.success == true) {
                        _isLoggedIn.emit(true)
                        _isToken.emit(loginResponse.data.token)
                        _userId.emit(loginResponse.data.id)
                        _userPhone.emit(loginResponse.data.phone)



                    } else {
                        _error.emit( loginResponse?.message)
                    }
                } else {
                    _error.emit(  response.message())

                }
            } catch (e: Exception) {
                _error.emit(  e.message)

            }

        }
    }
}


