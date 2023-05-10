package com.example.mygauth.core

import android.util.Log
import com.example.mygauth.core.Constants.TAG

class Utils {
  companion object {
    fun print(e:Exception) = Log.e(TAG,e.stackTraceToString())
  }
}