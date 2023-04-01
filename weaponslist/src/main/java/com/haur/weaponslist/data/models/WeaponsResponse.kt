package com.haur.weaponslist.data.models

data class WeaponsResponse(
    val status: Int,
    val data: List<WeaponItem>
) {
    data class WeaponItem(
        val uuid: String,
        val displayName: String,
        val displayIcon: String,
        val weaponStats: WeaponStats?,
        val shopData: ShopData?
    ) {

        data class WeaponStats(
            val fireRate: Float?
        )

        data class ShopData(
            val cost: Int,
            val categoryText: String
        )
    }
}