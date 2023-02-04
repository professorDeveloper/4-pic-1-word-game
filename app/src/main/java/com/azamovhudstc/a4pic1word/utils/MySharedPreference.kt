package com.azamovhudstc.a4pic1word.utils

import android.content.Context
import android.content.SharedPreferences


class MySharedPreference private constructor(context: Context) {
    var level: Int
        get() = sharedPreferences.getInt("level", 0)
        set(level) {
            editor.putInt("level", level).apply()
        }
    var coinCount: Int
        get() = sharedPreferences.getInt("coin", 100)
        set(count) {
            editor.putInt("coin", count).apply()
        }
    var itemClicked: Boolean
        get() = sharedPreferences.getBoolean("click", false)
        set(click) {
            editor.putBoolean("click", click).apply()
        }
    var sound: Boolean
        get() = sharedPreferences.getBoolean("sound", true)
        set(sound) {
            editor.putBoolean("sound", sound).apply()
        }
    var music: Boolean
        get() = sharedPreferences.getBoolean("music", true)
        set(music) {
            editor.putBoolean("music", music).apply()
        }
    var language: String?
        get() = sharedPreferences.getString("language", "eng")
        set(language) {
            editor.putString("language", language).apply()
        }

    companion object {
        var mySharedPreference: MySharedPreference? = null
        lateinit var sharedPreferences: SharedPreferences
        lateinit var editor: SharedPreferences.Editor
        fun getInstance(context: Context): MySharedPreference? {
            if (mySharedPreference == null) {
                mySharedPreference = MySharedPreference(context)
            }
            return mySharedPreference
        }
    }
    init {
        sharedPreferences = context.getSharedPreferences("app_name", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }
}
