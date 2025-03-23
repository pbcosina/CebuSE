package com.android.cebuse

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class LandingPageActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        val username = intent.getStringExtra("USERNAME")
        val welcomeText = findViewById<TextView>(R.id.welcomeText)
        val diveIn = findViewById<ImageView>(R.id.diveIn)

        welcomeText.text = "Hello, $username!"

        diveIn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}