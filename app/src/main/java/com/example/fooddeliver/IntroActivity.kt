package com.example.fooddeliver

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val startBtn: ConstraintLayout = findViewById(R.id.startBtn)
        startBtn.setOnClickListener {
            startActivity(Intent(this@IntroActivity, RegisterActivity::class.java))
        }
    }
}
