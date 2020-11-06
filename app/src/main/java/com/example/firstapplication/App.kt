package com.example.firstapplication

import android.app.Application

class App : Application() {

    companion object {
        private lateinit var context: Application

        fun getContext(): Application {
            return context
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}