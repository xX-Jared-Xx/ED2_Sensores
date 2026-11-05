package com.example.sensores4b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        val btnGravedad = this.findViewById<Button>(R.id.btnGravedad)
        val btnMagnetismo = this.findViewById<Button>(R.id.btnMagnetismo)

        btnGravedad.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btnMagnetismo.setOnClickListener {
            val intent = Intent(this, MagneticActivity::class.java)
            startActivity(intent)
        }


    }
}