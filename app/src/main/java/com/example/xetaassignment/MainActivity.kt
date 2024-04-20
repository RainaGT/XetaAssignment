package com.example.xetaassignment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        Handler().postDelayed({

            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)


            finish()
        }, 3000)
    }
}


