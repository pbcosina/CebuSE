package com.android.cebuse

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class ProfileActivity : Activity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_NAME = "UserPreferences"
    private val KEY_NAME = "user_name"
    private val KEY_EMAIL = "user_email"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        val profilePicture: ImageView = findViewById(R.id.profile_picture)
        val userNameEditText: EditText = findViewById(R.id.edit_user_name)
        val userEmailEditText: EditText = findViewById(R.id.edit_user_email)
        val editProfileButton: Button = findViewById(R.id.edit_profile_button)
        val btnBackToHome: Button = findViewById(R.id.btnBackToHome)

        val savedName = sharedPreferences.getString(KEY_NAME, "John Doe")
        val savedEmail = sharedPreferences.getString(KEY_EMAIL, "johndoe@example.com")

        userNameEditText.setText(savedName)
        userEmailEditText.setText(savedEmail)

        editProfileButton.setOnClickListener {
            val updatedName = userNameEditText.text.toString().trim()
            val updatedEmail = userEmailEditText.text.toString().trim()

            val oldName = sharedPreferences.getString(KEY_NAME, "")
            if (updatedName != oldName) {
                val editor = sharedPreferences.edit()
                editor.putString(KEY_NAME, updatedName)
                editor.putString(KEY_EMAIL, updatedEmail)
                editor.apply()

                Toast.makeText(this, "Profile updated successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No changes made.", Toast.LENGTH_SHORT).show()
            }
        }

        btnBackToHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}