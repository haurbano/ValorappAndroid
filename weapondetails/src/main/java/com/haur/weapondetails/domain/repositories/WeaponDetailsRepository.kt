package com.haur.weapondetails.domain.repositories

import com.haur.weapondetails.domain.models.WeaponDetails

interface WeaponDetailsRepository {
    suspend fun getWeaponDetails(weaponUUID: String): WeaponDetails
}