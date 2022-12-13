package com.example.warmindoonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.warmindoonline.R

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val btn_submit: Button = findViewById(R.id.btn_submit)
        val username: EditText = findViewById(R.id.etUsername)
        val email: EditText = findViewById(R.id.etEmail)
        val password: EditText = findViewById(R.id.etPassword)
        val btn_back: Button = findViewById(R.id.btn_back)

        btn_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        btn_submit.setOnClickListener{
            if (username.text.isBlank() == true && email.text.isBlank() == true && password.text.isBlank() == true)
                Toast.makeText(this, "Isi halaman kosng!", Toast.LENGTH_SHORT).show()
            else if (username.text.isBlank() == true && email.text.isBlank() == true)
                Toast.makeText(this, "Masukkan Username dan Email!", Toast.LENGTH_SHORT).show()
            else if (username.text.isBlank() == true && password.text.isBlank())
                Toast.makeText(this, "Masukkan Username dan Password!", Toast.LENGTH_SHORT).show()
            else if (password.text.isBlank() == true && email.text.isBlank() == true)
                Toast.makeText(this, "Masukkan Email dan Pasword!", Toast.LENGTH_SHORT).show()
            else if (username.text.isBlank() == true)
                Toast.makeText(this, "Masukkan Username!", Toast.LENGTH_SHORT).show()
            else if (email.text.isBlank() == true)
                Toast.makeText(this, "Masukkan Email!", Toast.LENGTH_SHORT).show()
            else if (password.text.isBlank() == true)
                Toast.makeText(this, "Masukkan Password!", Toast.LENGTH_SHORT).show()
            else if (username.text.isNotBlank() == true && email.text.isNotBlank() == true && password.text.isNotBlank() == true){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)}
            else {
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}