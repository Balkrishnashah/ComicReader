package com.alexcrue.comicreader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivtiy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_activtiy)
        Handler().postDelayed({startActivity(Intent(this,MainActivity::class.java))},3000)
    }
}
