package com.haur.weaponslist.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haur.weaponslist.R
import com.haur.weaponslist.domain.models.WeaponPreview
import com.squareup.picasso.Picasso

class WeaponsAdapter: RecyclerView.Adapter<WeaponsAdapter.WeaponsViewHolder>(){
    private val weapons = ArrayList<WeaponPreview>()
    private var onWeaponClicked: (String) -> Unit = {
        Log.w(WeaponsAdapter::class.java.toString(), "OnWeaponClicked callback is not set yet")
    }

    fun updateData(newWeapons: List<WeaponPreview>){
        weapons.clear()
        weapons.addAll(newWeapons)
        notifyDataSetChanged()
    }

    fun setOnWeaponClicked(callback: (String) -> Unit){
        onWeaponClicked = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.weapon_item, parent, false)

        return WeaponsViewHolder(view, onWeaponClicked)
    }

    override fun onBindViewHolder(holder: WeaponsViewHolder, position: Int) {
        holder.bind(weapons[position])
    }

    override fun getItemCount() = weapons.size

    class WeaponsViewHolder(
        private val viewItem: View,
        private val onWeaponClicked: (String) -> Unit
    ): RecyclerView.ViewHolder(viewItem) {
        private val tvName = viewItem.findViewById<TextView>(R.id.weaponNameTV)
        private val tvFireRate = viewItem.findViewById<TextView>(R.id.fireRateValueTV)
        private val tvCategory = viewItem.findViewById<TextView>(R.id.categoryValueTV)
        private val imgPreview = viewItem.findViewById<ImageView>(R.id.weaponItemImgPreview)

        fun bind(weaponPreview: WeaponPreview){
            tvName.text = weaponPreview.name
            tvFireRate.text = weaponPreview.fireRate.toString()
            tvCategory.text = weaponPreview.category
            Picasso.get().load(weaponPreview.image).into(imgPreview)
            viewItem.setOnClickListener { onWeaponClicked(weaponPreview.uuid) }
        }
    }
}