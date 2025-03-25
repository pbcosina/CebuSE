package com.android.cebuse

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern

class RegisterActivity : Activity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        val email = findViewById<EditText>(R.id.email)
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val confirmPassword = findViewById<EditText>(R.id.confirmPassword)
        val displayName = findViewById<EditText>(R.id.displayName)

        val submitButton = findViewById<Button>(R.id.register)
        val goToLoginButton = findViewById<Button>(R.id.goToLogin)

        submitButton.setOnClickListener {
            val emailText = email.text.toString().trim()
            val usernameText = username.text.toString().trim()
            val passwordText = password.text.toString().trim()
            val confirmPasswordText = confirmPassword.text.toString().trim()
            val displayNameText = displayName.text.toString().trim()

            if (emailText.isEmpty() || usernameText.isEmpty() || passwordText.isEmpty() || confirmPasswordText.isEmpty() || displayNameText.isEmpty()) {
                Toast.makeText(this, "Please fill out the form completely.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (!isValidEmail(emailText)) {
                Toast.makeText(this, "Invalid email format!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (passwordText.length < 6) {
                Toast.makeText(this, "Password must be at least 6 characters long.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (passwordText != confirmPasswordText) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            sharedPreferences.edit().apply {
                putString("username", usernameText)
                putString("email", emailText)
                putString("password", passwordText)
                putString("displayName", displayNameText)
                putBoolean("isRegistered", true)
                apply()
            }

            Toast.makeText(this, "Registration successful!", Toast.LENGTH_LONG).show()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        goToLoginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]{2,6}")
        return emailPattern.matcher(email).matches()
    }
}