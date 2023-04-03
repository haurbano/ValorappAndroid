package com.haur.weapondetails.data.datasources

import com.haur.weapondetails.data.models.WeaponDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface WeaponDetailsDataSource {
    @GET("weapons/{uuid}")
    suspend fun getDetails(@Path("uuid") uuid: String): WeaponDetailsResponse
}