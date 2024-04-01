package com.example.a121140014_pam_uts.utils

import android.content.Context
import android.content.SharedPreferences

class AuthPreferences(context: Context) {
    companion object {
        const val PREFS_NAME = "auth_preferences"
        const val IS_LOGGED_IN = "is_logged_in"
    }

    private val preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var isLoggedIn: Boolean
        get() = preferences.getBoolean(IS_LOGGED_IN, false)
        set(value) = preferences.edit().putBoolean(IS_LOGGED_IN, value).apply()
}
