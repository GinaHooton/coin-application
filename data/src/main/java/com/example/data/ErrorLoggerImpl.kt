package com.example.data

import com.example.domain.ErrorLogger
import android.util.Log
import javax.inject.Inject

class ErrorLoggerImpl @Inject constructor() : ErrorLogger {
    override fun error(tag: String, message: String, error: Throwable) {
        Log.e(tag, message, error)
    }
}