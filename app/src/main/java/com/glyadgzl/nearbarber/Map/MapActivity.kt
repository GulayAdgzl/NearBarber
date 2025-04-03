package com.glyadgzl.nearbarber.Map

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.glyadgzl.nearbarber.R

class MapActivity : AppCompatActivity() {
    private lateinit var item: StoreModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            item = intent.getSerializableExtra("object") as StoreModel
            
            val latitude = item.latitude
            val longitude = item.longitude
        }
    }
}