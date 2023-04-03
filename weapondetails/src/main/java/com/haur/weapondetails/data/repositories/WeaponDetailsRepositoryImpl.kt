package com.haur.weapondetails.data.repositories

import com.haur.weapondetails.data.datasources.WeaponDetailsDataSource
import com.haur.weapondetails.domain.models.WeaponDetails
import com.haur.weapondetails.domain.repositories.WeaponDetailsRepository

class WeaponDetailsRepositoryImpl(
    private val weaponDataSource: WeaponDetailsDataSource
): WeaponDetailsRepository {
    override suspend fun getWeaponDetails(weaponUUID: String): WeaponDetails {
        return weaponDataSource.getDetails(weaponUUID).data.run {
            WeaponDetails(
                uuid = uuid,
                name = displayName,
                fireRate = weaponStats.fireRate ?: 0f,
                cost = shopData.cost,
                category = shopData.categoryText,
                skins= skins?.map { WeaponDetails.Skin(it.displayIcon, it.displayName) }
            )
        }
    }
}