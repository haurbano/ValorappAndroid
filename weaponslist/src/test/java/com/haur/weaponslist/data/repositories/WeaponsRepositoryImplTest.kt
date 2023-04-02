package com.haur.weaponslist.data.repositories

import com.haur.weaponslist.data.datasources.WeaponsListDataSource
import com.haur.weaponslist.data.models.WeaponsResponse
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito

class WeaponsRepositoryImplTest {

    private val dataSource = Mockito.mock(WeaponsListDataSource::class.java)
    private val weaponsRepositoryImpl = WeaponsRepositoryImpl(dataSource)

    @Test
    fun `When I call getWeaponsList should get a list of WeaponPreview`(): Unit = runBlocking {
        // Given
        Mockito.`when`(dataSource.getWeapons()).thenReturn(buildMockWeaponResponse())

        // When
        val response = weaponsRepositoryImpl.getWeaponList()

        // Then
        assert(response.size == 5)
        assert(response[0].name == "Name-0")
        assert(response[2].image == "url-2")
    }

    private fun buildMockWeaponResponse(): WeaponsResponse {
        val items = ArrayList<WeaponsResponse.WeaponItem>()
        (0..4).forEach {
            items.add(WeaponsResponse.WeaponItem(
                uuid= "UUID-$it",
                displayName = "Name-$it",
                displayIcon = "url-$it",
                weaponStats = WeaponsResponse.WeaponItem.WeaponStats(
                    fireRate = 0f+it
                ),
                shopData = WeaponsResponse.WeaponItem.ShopData(
                    cost= it,
                    categoryText = "Category=$it"
                )
            ))
        }
        return WeaponsResponse(200, items)
    }
}