package com.example.warmindoonline

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class DashboardPayment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_payment)

        val btnLogout: Button = findViewById(R.id.btnLogout)
        val btnBack: Button = findViewById(R.id.btnBack)
        val btnNSubmit: Button = findViewById(R.id.btnSubmit)

        btnLogout.setOnClickListener {
            val intent: Intent =
                Intent(this, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            finish()
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        btnBack.setOnClickListener {
            intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        btnNSubmit.setOnClickListener {

            val nama: EditText = findViewById(R.id.nama)
            val no_meja: EditText = findViewById(R.id.no_meja)
            val pesanan: EditText = findViewById(R.id.pesanan)
            val jml_pesanan: EditText = findViewById(R.id.jml_pesanan)

            //Intent ke WhatsApp menggunakan APi dan Menambahkan Pesan
            try {
                val mobile = "6287735593313"
                val msg = ("NAMA:   (" + nama.text.toString() + ")")
                val msgnomeja = ("\nNO MEJA:   (" + no_meja.text.toString() + ")")
                val msgpesanan = ("\nPESANAN:   (" + pesanan.text.toString() + ")")
                val msgjml = ("\nJUMLAH:   (" + jml_pesanan.text.toString() + ")")
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://api.whatsapp.com/send?phone=$mobile&text=$msg$msgnomeja$msgpesanan$msgjml")
                    )
                )
            } catch (e: Exception) {
                //whatsapp tak terInstall
            }
        }
    }
}