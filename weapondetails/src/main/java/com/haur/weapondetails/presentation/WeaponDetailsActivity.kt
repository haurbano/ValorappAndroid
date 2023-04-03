package com.haur.weapondetails.presentation

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haur.weapondetails.R
import com.haur.weapondetails.domain.models.WeaponDetails
import org.koin.android.ext.android.inject

class WeaponDetailsActivity : AppCompatActivity() {

    private val viewModel: WeaponDetailsViewModel by inject()
    private val skinsAdapter: SkinsAdapter by inject()

    private lateinit var progressBar: ProgressBar
    private lateinit var skinsRecycler: RecyclerView
    private lateinit var fireRateTV: TextView
    private lateinit var costTV: TextView
    private lateinit var categoryTV: TextView
    private lateinit var nameTv: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weapon_detials)

        val uuid = intent.getStringExtra(WEAPON_UUID_KEY)

        uuid?.let {
            retrieveViews()
            configureSkinsRecycler()
            observeUIState()
            viewModel.fetchWeaponDetails(uuid)
        } ?: showError()

    }

    private fun retrieveViews() {
        progressBar = findViewById(R.id.progressBarWeaponDetails)
        skinsRecycler = findViewById(R.id.rv_skins)
        fireRateTV = findViewById(R.id.tv_fire_rate)
        costTV = findViewById(R.id.tv_cost)
        categoryTV = findViewById(R.id.tv_category)
        nameTv = findViewById(R.id.tv_weapon_name)
    }

    private fun configureSkinsRecycler() {
        skinsRecycler.apply {
            layoutManager = LinearLayoutManager(
                this@WeaponDetailsActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = skinsAdapter
            addItemDecoration(SpacesItemDecoration(16))
        }
    }

    private fun observeUIState() {
        viewModel.uiState .observe(this) { uiState ->
            when(uiState){
                is WeaponDetailsViewModel.UIState.Error -> showError()
                WeaponDetailsViewModel.UIState.Loading -> showLoading()
                is WeaponDetailsViewModel.UIState.Success -> showContent(uiState.weaponDetails)
            }
        }
    }

    private fun showContent(weaponDetails: WeaponDetails) {
        progressBar.visibility = View.GONE
        with(weaponDetails) {
            skinsAdapter.updateData(skins)

            fireRateTV.text = "Fire Rate: $fireRate"
            costTV.text = "Cost: $cost"
            categoryTV.text = "Category: $category"
            nameTv.text = name
        }
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun showError() {
        progressBar.visibility = View.GONE
        Toast.makeText(this, "Something went wrong, sorry", Toast.LENGTH_LONG).show()
    }

    companion object {
        const val WEAPON_UUID_KEY = "weapon_uuid"
    }
}

class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = space
        outRect.right = space
    }
}