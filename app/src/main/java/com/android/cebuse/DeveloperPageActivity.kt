package com.android.cebuse

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class DeveloperPageActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer_page)

        val backToHomeButton: Button = findViewById(R.id.button_back_home)
        backToHomeButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}