package com.example.uso_de_libreria

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.viewpager2.widget.ViewPager2
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var imageSlider: ViewPager2
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var handler: Handler
    private val sliderRunnable = Runnable {
        imageSlider.currentItem = (imageSlider.currentItem + 1) % sliderAdapter.itemCount
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageSlider = findViewById(R.id.imageSlider)
        val btnOfficialSite: Button = findViewById(R.id.btnOfficialSite)
        val tvTitle: TextView = findViewById(R.id.tvTitle)
        val tvReleaseDate: TextView = findViewById(R.id.tvReleaseDate)
        val tvMainActors: TextView = findViewById(R.id.tvMainActors)
        val tvDescription: TextView = findViewById(R.id.tvDescription)

        val images = listOf(
            R.drawable.imagen1,
            R.drawable.imagen2,
            R.drawable.imagen3,
            R.drawable.imagen4,
            R.drawable.imagen5,
            R.drawable.imagen6
        )

        sliderAdapter = SliderAdapter(images)
        imageSlider.adapter = sliderAdapter


        handler = Handler(Looper.getMainLooper())
        imageSlider.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(sliderRunnable)
                handler.postDelayed(sliderRunnable, 3000) // Cambia de imagen cada 3 segundos
            }
        })


        tvTitle.text = "Transformers: La era de la extinción"
        tvReleaseDate.text = "Fecha de Lanzamiento: 01/01/2014"
        tvMainActors.text = "Actores Principales: Mark Wahlberg, Stanley Tucci y Nicola Peltz Beckham"
        tvDescription.text = "Descripción: Cinco años después de la destrucción de Chicago, los humanos están en contra de los robots. Pero un padre soltero e inventor resucitará a uno que salvará al mundo."

        btnOfficialSite.setOnClickListener {
            val url = "https://www.netflix.com/sv/title/70299855"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(sliderRunnable, 3000)
    }
}
