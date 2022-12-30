package com.example.warmindoonline

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat.postDelayed

private val waktu_loading = 2000

//4000=4 detik
class LandingPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

            supportActionBar?.hide()

        Handler().postDelayed({ //setelah loading maka akan langsung berpindah ke home activity
            val home = Intent(this, MainActivity::class.java)
            startActivity(home)
            finish()
        }, waktu_loading.toLong())
    }
}