package com.android.cebuse

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class LoginActivity : Activity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        val usernameEditText = findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val rememberMeCheckBox = findViewById<CheckBox>(R.id.remember_me)
        val registerButton = findViewById<Button>(R.id.register)
        val loginButton = findViewById<Button>(R.id.login)

        val savedUsername = sharedPreferences.getString("username", "")
        val savedPassword = sharedPreferences.getString("password", "")
        val isRemembered = sharedPreferences.getBoolean("rememberMe", false)
        val isRegistered = sharedPreferences.getBoolean("isRegistered", false)

        if (isRemembered) {
            usernameEditText.setText(savedUsername)
            passwordEditText.setText(savedPassword)
            rememberMeCheckBox.isChecked = true
        }

        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isEmpty()) {
                usernameEditText.error = "Username cannot be empty!"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                passwordEditText.error = "Password cannot be empty!"
                return@setOnClickListener
            }

            if (!isRegistered) {
                Toast.makeText(this, "You need to register first!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (username == savedUsername && password == savedPassword) {
                if (rememberMeCheckBox.isChecked) {
                    sharedPreferences.edit().apply {
                        putBoolean("rememberMe", true)
                        apply()
                    }
                } else {
                    sharedPreferences.edit().remove("rememberMe").apply()
                }

                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, LandingPageActivity::class.java)
                intent.putExtra("USERNAME", username)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "You need to register first!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}