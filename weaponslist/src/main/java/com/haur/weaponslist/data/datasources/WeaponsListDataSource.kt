package com.haur.weaponslist.data.datasources

import com.haur.weaponslist.data.models.WeaponsResponse
import retrofit2.http.GET

interface WeaponsListDataSource {
    @GET("weapons")
    suspend fun getWeapons(): WeaponsResponse
}