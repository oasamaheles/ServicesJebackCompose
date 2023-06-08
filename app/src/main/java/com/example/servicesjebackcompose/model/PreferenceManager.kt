package com.example.servicesjebackcompose.model

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(private val context: Context) {

    companion object {
        private const val PREF_NAME = "preference_data"
        private const val KEY_TOKEN = "token"
        private const val KEY_USER_ID = "userId"
        private const val KEY_USER_PHONE = "userPhone"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_TOKEN, "Bearer $token").apply()
    }

    fun saveUserId(userId: Int) {
        sharedPreferences.edit().putInt(KEY_USER_ID, userId).apply()
    }

    fun getUserID (): Int {
        return sharedPreferences.getInt(KEY_USER_ID, -1)

    }

    fun deleteUserId() {
        sharedPreferences.edit().remove(KEY_USER_ID).apply()

    }

    fun deleteUserUserPhone() {
        sharedPreferences.edit().remove(KEY_USER_PHONE).apply()

    }

    fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }

    fun getUserPhone(): String? {
        return sharedPreferences.getString(KEY_USER_PHONE, null)
    }

    fun saveUserPhone(userPhone: String) {
        sharedPreferences.edit().putString(KEY_USER_PHONE, userPhone).apply()
    }


    fun deleteToken() {
        sharedPreferences.edit().remove(KEY_TOKEN).apply()
    }


}
