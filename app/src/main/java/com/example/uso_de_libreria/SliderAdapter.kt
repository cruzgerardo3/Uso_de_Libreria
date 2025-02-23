package com.example.uso_de_libreria

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.uso_de_libreria.R
import com.squareup.picasso.Picasso

class SliderAdapter(private val imageList: List<Int>) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgSlider: ImageView = itemView.findViewById(R.id.imgSlider)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slider_item, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        // Cargar la imagen utilizando Picasso
        Picasso.get().load(imageList[position]).into(holder.imgSlider)
    }

    override fun getItemCount(): Int = imageList.size
}
