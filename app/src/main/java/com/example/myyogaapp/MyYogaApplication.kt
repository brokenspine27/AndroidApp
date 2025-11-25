package com.example.myyogaapp

import android.app.Application
import com.google.firebase.FirebaseApp

class MyYogaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}
