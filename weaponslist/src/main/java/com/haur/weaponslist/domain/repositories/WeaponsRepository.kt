package com.haur.weaponslist.domain.repositories

import com.haur.weaponslist.domain.models.WeaponPreview

interface WeaponsRepository {
    suspend fun getWeaponList(): List<WeaponPreview>
}