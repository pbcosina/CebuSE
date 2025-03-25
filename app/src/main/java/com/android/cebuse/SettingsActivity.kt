package com.android.cebuse

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast

class SettingsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val backToHomeButton = findViewById<Button>(R.id.backToHomeButton)
        val logoutButton = findViewById<Button>(R.id.logoutButton)
        val aboutDevelopersButton = findViewById<Button>(R.id.aboutDevelopersButton)
        val notificationSwitch = findViewById<Switch>(R.id.notificationSwitch)

        val sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE)
        val isChecked = sharedPreferences.getBoolean("notifications_enabled", false)
        notificationSwitch.isChecked = isChecked

        notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Notifications Enabled", Toast.LENGTH_SHORT).show()
                // yet to add logic to really enable notifs
            } else {
                Toast.makeText(this, "Notifications Disabled", Toast.LENGTH_SHORT).show()
                // yet to add logic to really enable notifs
            }

            // to save the switch state (whether it's on or off)
            val editor = sharedPreferences.edit()
            editor.putBoolean("notifications_enabled", isChecked)
            editor.apply()
        }

        aboutDevelopersButton.setOnClickListener {
            val intent = Intent(this, DeveloperPageActivity::class.java)
            startActivity(intent)
        }

        backToHomeButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        logoutButton.setOnClickListener {
            showLogoutConfirmationDialog()
        }
    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirm Logout")
        builder.setMessage("Are you sure you want to log out?")

        builder.setPositiveButton("Yes") { _, _ ->
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}