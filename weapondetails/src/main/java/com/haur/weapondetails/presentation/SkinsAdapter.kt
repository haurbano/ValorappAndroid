package com.haur.weapondetails.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haur.weapondetails.R
import com.haur.weapondetails.domain.models.WeaponDetails
import com.squareup.picasso.Picasso

class SkinsAdapter : RecyclerView.Adapter<SkinsAdapter.SkinViewHolder>() {

    private val skins = ArrayList<WeaponDetails.Skin>()

    fun updateData(data: List<WeaponDetails.Skin>?){
        data?.let {
            skins.clear()
            skins.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weapon_details, parent, false)
        return SkinViewHolder(view)
    }

    override fun onBindViewHolder(holder: SkinViewHolder, position: Int) {
        holder.bind(skins[position])
    }

    override fun getItemCount() = skins.size

    inner class SkinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgSkin = itemView.findViewById<ImageView>(R.id.img_skin)
        private val skinName = itemView.findViewById<TextView>(R.id.tv_skin_name)

        fun bind(skin: WeaponDetails.Skin) {
            Picasso.get().load(skin.image).into(imgSkin)
            skinName.text = skin.name
        }
    }
}