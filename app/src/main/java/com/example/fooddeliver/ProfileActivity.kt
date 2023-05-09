package com.example.fooddeliver

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.SharedPreferences
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class ProfileActivity : AppCompatActivity() {
    private lateinit var signout: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val name = findViewById<TextView>(R.id.textView8)
        val loggedInUser = intent.getStringExtra("name")
        name.setText("Welcome "+ loggedInUser)
        signOutButton()
        bottomNavigation(loggedInUser)
    }

    private fun signOutButton() {
        signout = findViewById(R.id.btnSignOut)
        signout.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googlesigninclient = GoogleSignIn.getClient(this,gso)
            googlesigninclient.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun bottomNavigation(user: String?) {
        val homeBtn = findViewById<LinearLayout>(R.id.homeBtn)
        val cartBtn = findViewById<LinearLayout>(R.id.cartBtn)
        val profileBtn = findViewById<LinearLayout>(R.id.profileBtn)

        homeBtn.setOnClickListener {
            val intent = Intent(this@ProfileActivity, MainActivity::class.java)
            intent.putExtra("name", user)
            startActivity(intent)
        }

        cartBtn.setOnClickListener {
            val intent = Intent(this@ProfileActivity, CartActivity::class.java)
            intent.putExtra("name", user)
            startActivity(intent)
        }

        profileBtn.setOnClickListener {
            val intent = Intent(this@ProfileActivity, ProfileActivity::class.java)
            intent.putExtra("name", user)
            startActivity(intent)
        }
    }
}