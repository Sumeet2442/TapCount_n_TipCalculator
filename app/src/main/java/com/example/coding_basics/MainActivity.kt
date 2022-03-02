package com.example.coding_basics

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var taps:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textHeader: TextView = findViewById(R.id.textHead)
        val textSummary: TextView = findViewById(R.id.textSum)
        textHeader.text= "This is my App"
        textSummary.text = "This is the very first app i created using kotlin"
        val btnTap:Button = findViewById(R.id.btnTap)
        val tapCount:TextView = findViewById(R.id.tapCount)
        val btnNext:Button = findViewById(R.id.btnNextPage)

        btnTap.setOnClickListener {
            taps++
            tapCount.text = "Tap Count - ${taps}"
        }
        btnNext.setOnClickListener {
            val Intent = Intent(this,tipcalculator::class.java)
            startActivity(Intent)
        }
    }
}