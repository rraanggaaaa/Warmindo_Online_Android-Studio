package com.example.warmindoonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DashboardPayment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_payment)

        val btnLogout: Button = findViewById(R.id.btnLogout)
        val btnBack: Button = findViewById(R.id.btnBack)
        val btnNext: Button = findViewById(R.id.btnNext)

        btnLogout.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            finish()
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        btnBack.setOnClickListener{
            intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        btnNext.setOnClickListener{
            intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

    }
}