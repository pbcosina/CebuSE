package com.android.cebuse

import android.app.Application
import android.util.Log
class CebuSE : Application() {
    private var user = User()

    override fun onCreate() {
        super.onCreate()
        Log.e("Application", "onCreate is called")
    }

    fun setUser(user: User) {
        this.user = user
    }

    fun getUser(): User = this.user
}

class User {
    var username: String = ""
    var email: String = ""
}