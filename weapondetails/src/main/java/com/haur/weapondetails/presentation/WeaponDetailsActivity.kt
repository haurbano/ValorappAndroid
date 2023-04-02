package com.haur.weapondetails.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.haur.weapondetails.R

class WeaponDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weapon_detials)

        val uuid = intent.getStringExtra("weapon_uuid")
        uuid?.let {
            findViewById<TextView>(R.id.textView).text = it
        }
    }
}