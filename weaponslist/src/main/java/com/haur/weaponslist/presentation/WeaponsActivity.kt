package com.haur.weaponslist.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haur.weaponslist.R
import org.koin.android.ext.android.inject

class WeaponsActivity : AppCompatActivity() {
    private lateinit var recyclerWeapons: RecyclerView

    private val viewModel: WeaponsViewModel by inject()
    private val weaponsAdapter: WeaponsAdapter by inject()

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weapons)
        progressBar = findViewById(R.id.weaponsProgressBar)
        setupWeaponsRecyclerView()
        listenUIChanges()
        viewModel.fetchWeapons()
    }

    private fun listenUIChanges() {
        viewModel.uiState.observe(this) { uiState ->
            when {
                uiState.isLoading -> progressBar.visibility = View.VISIBLE
                else -> {
                    progressBar.visibility = View.GONE
                    weaponsAdapter.updateData(uiState.weapons)
                }
            }
        }
    }

    private fun setupWeaponsRecyclerView() {
        weaponsAdapter.setOnWeaponClicked { navigateToDetails(it) }
        recyclerWeapons = findViewById(R.id.recyclerWeapons)
        recyclerWeapons.apply {
            adapter = weaponsAdapter
            layoutManager = LinearLayoutManager(this@WeaponsActivity)
        }
    }

    private fun navigateToDetails(uuid: String){
        val uri = Uri.parse(WEAPON_DETAILS_DEEPLINK)
        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
            putExtra(WEAPON_UUID, uuid)
        }
        startActivity(intent)
    }

    companion object {
        const val WEAPON_DETAILS_DEEPLINK = "valorapp://weapondetails"
        const val WEAPON_UUID = "weapon_uuid"
    }
}