package com.nt.nt_notify

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

// added android:usesCleartextTraffic="true" in manifest
// socket failed: EPERM (Operation not permitted)
private val retrofit = Retrofit.Builder().baseUrl("http://localhost:8765/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService: ApiService = retrofit.create(ApiService::class.java)

@Parcelize
data class UserCredentials(val username: String, val password: String): Parcelable

@Parcelize
data class Auth(val token: String): Parcelable

@Parcelize
data class Category
    (val idCategory: Int,
     val strCategory: String,
     val strCategoryThumb: String,
     val strCategoryDescription: String): Parcelable

// http://localhost:8765/security-service/api/v1/public/authenticate
// post
// userCred = { email: email, password: password }

data class Categories(val categories: List<Category>)

interface ApiService {

    // Example
    @GET("categories.php")
    suspend fun getCategories(): Categories

    @POST("security-service/api/v1/public/authenticate")
    suspend fun authenticate(@Body userCredentials: UserCredentials): Auth

}