package com.android.cebuse

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView

class HomeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val profileIcon = findViewById<ImageView>(R.id.profileIcon)
        val settingsIcon = findViewById<ImageView>(R.id.settingsIcon)
        val headToLocation = findViewById<ImageView>(R.id.ic_headToLocation)

        profileIcon.setOnClickListener {
            val intent = Intent (this, ProfileActivity::class.java)
             startActivity(intent)
        }

        settingsIcon.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        headToLocation.setOnClickListener {
            // ADD ANOTHER LAYOUT THAT SHOULD BE THE PLACES THINGY
        }
    }
}