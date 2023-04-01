package com.haur.weaponslist.domain.models

data class WeaponPreview(
    val uuid: String,
    val name: String,
    val image: String,
    val fireRate: Int,
    val category: String
)
