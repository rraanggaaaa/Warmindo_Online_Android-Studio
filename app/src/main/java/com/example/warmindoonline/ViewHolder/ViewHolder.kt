package com.example.warmindoonline.ViewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.warmindoonline.Data.Food
import com.example.warmindoonline.R


class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.view_item, parent, false))
{
    private lateinit var imageView: ImageView
    private lateinit var tv_title : TextView
    private lateinit var tv_deskripsi : TextView
    private lateinit var tv_rating_food : TextView

    init {
        imageView = itemView.findViewById(R.id.iv_list)
        tv_title = itemView.findViewById(R.id.tv_harga)
        tv_deskripsi = itemView.findViewById(R.id.tv_deskripsi)
        tv_rating_food = itemView.findViewById(R.id.tv_rating_food)
    }

    fun bind(data: Food){
        imageView.setImageResource(data.ImageView)
        tv_title.text = data.tv_harga
        tv_deskripsi.text = data.tv_deskripsi
        tv_rating_food.text = data.tv_rating_food
    }
}