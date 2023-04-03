package com.haur.weapondetails.data.models

data class WeaponDetailsResponse(
    val status: Int,
    val data: Weapon
){
    data class Weapon(
        val uuid: String,
        val displayName: String,
        val weaponStats: WeaponStats?,
        val shopData: ShopData?,
        val skins: List<Skin>?
    ){
        data class WeaponStats(
            val fireRate: Float?
        )

        data class ShopData(
            val cost: Int,
            val categoryText: String
        )

        data class Skin(
            val displayName: String,
            val displayIcon: String
        )
    }
}