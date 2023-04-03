package com.haur.weapondetails.domain.models

data class WeaponDetails(
    val uuid: String,
    val name: String,
    val fireRate: Float,
    val cost: Int,
    val category: String,
    val skins: List<Skin>?
){
    data class Skin(
        val image: String?,
        val name: String?
    )
}