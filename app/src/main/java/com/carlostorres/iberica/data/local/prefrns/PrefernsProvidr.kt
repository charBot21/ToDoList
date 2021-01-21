package com.carlostorres.iberica.data.local.prefrns

import android.content.Context

class PrefernsProvidr (context: Context) {

    private val sharedPreferences = context.getSharedPreferences("loginPreferences", 0)

    fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String) : Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }
}