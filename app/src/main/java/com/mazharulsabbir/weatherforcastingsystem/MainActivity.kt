package com.mazharulsabbir.weatherforcastingsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getWeatherData()
    }

    private fun getWeatherData() {
        val mRef = FirebaseDatabase.getInstance().reference
        mRef.child("weather_station")
                .child("data")
                .child("lm35")
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                        Logger.getLogger("getWeatherData").warning("Error: ${error.message}")
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        Logger.getLogger("getWeatherData").warning("Response: ${snapshot.value}")
                        temp.text = snapshot.value.toString()
                    }
                })
    }
}