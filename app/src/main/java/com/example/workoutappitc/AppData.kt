package com.example.workoutappitc

import android.app.Application
import com.example.workoutappitc.api.models.User

class AppData : Application() {
    companion object {
        var user: User? = null
    }
}