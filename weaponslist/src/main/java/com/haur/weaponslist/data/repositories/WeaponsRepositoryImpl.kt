package com.haur.weaponslist.data.repositories

import com.haur.weaponslist.data.datasources.WeaponsListDataSource
import com.haur.weaponslist.domain.models.WeaponPreview
import com.haur.weaponslist.domain.repositories.WeaponsRepository

class WeaponsRepositoryImpl(
    private val weaponsListDataSource: WeaponsListDataSource
): WeaponsRepository {

    override suspend fun getWeaponList(): List<WeaponPreview> {
        val respsonse = weaponsListDataSource.getWeapons()
        return respsonse.data.map { weaponItem ->
            with(weaponItem){
                WeaponPreview(
                    uuid = uuid,
                    name = displayName,
                    image = displayIcon,
                    fireRate = weaponStats?.fireRate?.toInt() ?: 0,
                    category = shopData?.categoryText ?: "NO Category"
                )
            }
        }
    }
}