package com.pluralsight.recviewkaptest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.pluralsight.recviewkaptest.Activity.Intro

class Session(internal var _context: Context) {
    // Shared Preferences
    internal var pref: SharedPreferences

    // Editor for Shared preferences
    internal var editor: SharedPreferences.Editor

    // Shared pref mode
    internal var PRIVATE_MODE = 0

    val isFilled: Boolean?
        get() = pref.getBoolean(KEY_HAS_FILLED, false)

    val first_page: Int?
        get() = pref.getInt(KEY_FIRST_PAGE, 0)

    val next_page: Int?
        get() = pref.getInt(KEY_NEXT_PAGE, 0)

    init {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    fun hasFilled(filled: Boolean) {
        editor.putBoolean(KEY_HAS_FILLED, filled)
        editor.commit()
    }

    fun setFirstPageSize(size: Int) {
        editor.putInt(KEY_FIRST_PAGE, size)
        editor.commit()
    }

    fun setNextPageSize(size: Int) {
        editor.putInt(KEY_NEXT_PAGE, size)
        editor.commit()
    }

    fun checkFilled(){
        // Check fill status
        if (this.isFilled == false) {
            // exampeModel is not logged in redirect him to Login ActivityLog
            editor.clear()
            editor.commit()

            val i = Intent(_context, Intro::class.java)
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            // Add new Flag to start new ActivityLog
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            // Staring Login ActivityLog
            _context.startActivity(i)
        }
    }

//    fun logout() {
//        // Clearing all data from Shared Preferences
//        editor.clear()
//        editor.commit()
//
//        // After logout redirect exampeModel to Loing ActivityLog
//        val i = Intent(_context, Intro::class.java)
//        // Closing all the Activities
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//
//        // Staring Login ActivityLog
//        _context.startActivity(i)
//    }

    companion object {

        // Sharedpref file name
        private val PREF_NAME = "KDSPref"

        // All Shared Preferences Keys
        val KEY_HAS_FILLED      = "filled"
        val KEY_FIRST_PAGE      = "firstPage"
        val KEY_NEXT_PAGE       = "nextPage"

        private var instance: Session? = null

        fun with(context: Context): Session {

            if (instance == null) {
                instance = Session(context)
            }
            return instance as Session
        }
    }

}