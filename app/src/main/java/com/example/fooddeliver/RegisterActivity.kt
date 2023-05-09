package com.example.fooddeliver

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    private lateinit var fbBtn: ImageView
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var repassword: EditText
    private lateinit var signin: Button
    private lateinit var signup: Button
    private lateinit var DB: DBHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        repassword = findViewById(R.id.repassword)
        signin = findViewById(R.id.btnsignin)
        signup = findViewById(R.id.btnsignup)
        fbBtn = findViewById(R.id.fbBtn)
        DB = DBHelper(this)

        signup.setOnClickListener{
            val user = username.text.toString()
            val pass = password.text.toString()
            val repass = repassword.text.toString()

            if(user.isEmpty() || pass.isEmpty() || repass.isEmpty()){
                Toast.makeText(this, "Please enter all the fields", Toast.LENGTH_SHORT).show()
            }else{
                if(pass == repass){
                    val checkUser = DB.checkUsername(user)
                    if(!checkUser){
                        val insert = DB.insertData(user, pass)
                        if(insert){
                            Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this, "User already exists! please sign in", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "Password not matching", Toast.LENGTH_SHORT).show()
                }
            }
        }

        signin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        fbBtn.setOnClickListener{
            // TODO: Implement Facebook login functionality
        }
    }
}
