package com.android.cebuse

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Patterns
import android.widget.*
import androidx.core.content.ContextCompat

class ProfileActivity : Activity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var profileLayout: LinearLayout
    private lateinit var profilePicture: ImageView
    private lateinit var userNameEditText: EditText
    private lateinit var userEmailEditText: EditText
    private lateinit var userBioEditText: EditText

    private val PREFS_NAME = "UserPreferences"
    private val KEY_NAME = "user_name"
    private val KEY_EMAIL = "user_email"
    private val KEY_BIO = "user_bio"
    private val KEY_BG_COLOR = "bg_color"
    private val KEY_PROFILE_URI = "profile_uri"

    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        profileLayout = findViewById(R.id.profile_layout)
        profilePicture = findViewById(R.id.profile_picture)
        userNameEditText = findViewById(R.id.edit_user_name)
        userEmailEditText = findViewById(R.id.edit_user_email)
        userBioEditText = findViewById(R.id.edit_user_bio)
        val editProfileButton: Button = findViewById(R.id.edit_profile_button)
        val btnBackToHome: Button = findViewById(R.id.btnBackToHome)

        val colorButtons = listOf(
            Pair(R.id.color_red, "#F44336"),
            Pair(R.id.color_blue, "#2196F3"),
            Pair(R.id.color_purple, "#9C27B0"),
            Pair(R.id.color_pink, "#E91E63"),
            Pair(R.id.color_black, "#000000")
        )

        userNameEditText.setText(sharedPreferences.getString(KEY_NAME, "John Doe"))
        userEmailEditText.setText(sharedPreferences.getString(KEY_EMAIL, "johndoe@example.com"))
        userBioEditText.setText(sharedPreferences.getString(KEY_BIO, ""))

        val savedColor = sharedPreferences.getString(KEY_BG_COLOR, "#FFFFFF")
        profileLayout.setBackgroundColor(Color.parseColor(savedColor))

        val savedUri = sharedPreferences.getString(KEY_PROFILE_URI, null)
        if (savedUri != null) {
            profilePicture.setImageURI(Uri.parse(savedUri))
        }

        editProfileButton.setOnClickListener {
            val name = userNameEditText.text.toString().trim()
            val email = userEmailEditText.text.toString().trim()
            val bio = userBioEditText.text.toString().trim()

            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Name and Email cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Invalid Email Format", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val editor = sharedPreferences.edit()
            editor.putString(KEY_NAME, name)
            editor.putString(KEY_EMAIL, email)
            editor.putString(KEY_BIO, bio)
            editor.apply()

            Toast.makeText(this, "Profile saved", Toast.LENGTH_SHORT).show()
        }

        colorButtons.forEach { (buttonId, colorCode) ->
            findViewById<Button>(buttonId).setOnClickListener {
                profileLayout.setBackgroundColor(Color.parseColor(colorCode))
                sharedPreferences.edit().putString(KEY_BG_COLOR, colorCode).apply()
            }
        }

        profilePicture.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        btnBackToHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            val imageUri: Uri? = data.data
            imageUri?.let {
                profilePicture.setImageURI(it)
                sharedPreferences.edit().putString(KEY_PROFILE_URI, it.toString()).apply()
            }
        }
    }
}