package com.example.domain

interface ErrorLogger {
    fun error(tag: String, message: String, error: Throwable)
}