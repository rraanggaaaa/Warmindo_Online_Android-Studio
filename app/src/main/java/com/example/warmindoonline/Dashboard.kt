package com.example.warmindoonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.warmindoonline.R

class Dashboard : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val btn_back: Button = findViewById(R.id.btn_back)



        btn_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        init()
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
    }

    private fun init() {
        recyclerView = findViewById(R.id.rv_itemlist)

        var data = ArrayList<Food>()
//Makanan
        data.add(Food(R.drawable.photo_mie, "Rp.15.000", "Mie", "(97)"))
        data.add(Food(R.drawable.photo_magelangan, "Rp.15.000", "Magelangan", "(65)"))
        data.add(Food(R.drawable.photo_sarden, "Rp.10.000", "Nasi Sarden", "(43)"))
        data.add(Food(R.drawable.photo_ayygor, "Rp.12.000", "Nasi Ayam Goreng", "(89)"))
        data.add(Food(R.drawable.photo_aygep, "Rp.14.000", "Nasi Ayam Geprek", "(77)"))
        data.add(Food(R.drawable.photo_terbal, "Rp.10.000", "Nasi Telur Balado", "(47)"))
        data.add(Food(R.drawable.photo_telcep, "Rp.9.000", "Nasi Telur Ceplok", "(59)"))
        data.add(Food(R.drawable.photo_teldar, "Rp.9.000", "Nasi Telur Dadar", "(27)"))
        data.add(Food(R.drawable.photo_orar, "Rp.10.000", "Nasi Orak Arik", "(69)"))
        data.add(Food(R.drawable.photo_nasgor, "Rp.20.000", "Nasi Goreng Spesial", "(85)"))
        data.add(Food(R.drawable.photo_nasi, "Rp.3.000", "Nasi Hangat", "(32)"))
        data.add(Food(R.drawable.photo_omelete, "Rp.12.000", "Nasi Omelete", "(99)"))
        data.add(Food(R.drawable.photo_soto, "Rp.8.000", "Soto Ayam", "(97)"))

//Sampingan
        data.add(Food(R.drawable.photo_kerupuk, "Rp.2.000", "Kerupuk", "(48)"))
        data.add(Food(R.drawable.photo_mendoan, "Rp.2.000", "Mendoan", "(83)"))


//Minuman
        data.add(Food(R.drawable.photo_air, "Rp.3000", "Air Mineral", "(270)"))
        data.add(Food(R.drawable.photo_teh, "Rp.3000", "Es Teh", "(154)"))
        data.add(Food(R.drawable.photo_esjeruk, "Rp.4.000", "Es Jeruk", "(120)"))
        data.add(Food(R.drawable.photo_wedjah, "Rp.5.000", "Wedang Jahe", "(27)"))
        data.add(Food(R.drawable.photo_kopi, "Rp.5.000", "Kopi", "(105)"))
        data.add(Food(R.drawable.photo_jus, "Rp.7000", "Aneka Jus", "(64)"))

        adapter = MyAdapter(data)
    }
}