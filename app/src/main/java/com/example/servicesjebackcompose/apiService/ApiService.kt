package com.example.servicesjebackcompose.apiService

import com.and.dev.homeservice.model.*
import com.example.servicesjebackcompose.model.AllWorkResponse
import com.example.servicesjebackcompose.model.CreateOrderRequestResponse
import com.example.servicesjebackcompose.model.LoginResponse
import com.example.servicesjebackcompose.model.RegisterResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {


    @FormUrlEncoded
    @POST("auth/login/user")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>

    @FormUrlEncoded
    @POST("auth/register/user")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("phone") phone: String,
    ): Response<RegisterResponse>

    @FormUrlEncoded
    @POST("create/order")
    suspend fun createOrder(
        @Header("Authorization") token: String,
        @Field("user_id") userId: Int,
        @Field("work_id") workId: Int,
        @Field("details") details: String,
        @Field("details_address") detailsAddress: String,
        @Field("lat") lat: String,
        @Field("long") long: String,
        @Field("phone") phone: String,
    ): Response<CreateOrderRequestResponse>


    @GET("all/works")
    suspend fun getAllWorkResponse(): AllWorkResponse

    @GET("order/un/complete/user")
    suspend fun getUnCompletedOrder(
        @Header("Authorization") token: String
    ): CreateOrderRequestResponse

    @GET("order/pending/user")
    suspend fun getPendingOrders(@Header("Authorization") token: String): CreateOrderRequestResponse

    @GET("order/complete/user")
    suspend fun getCompletedOrder(@Header("Authorization") token: String): CreateOrderRequestResponse


    companion object {
        private const val BASE_URL = "https://studentucas.awamr.com/api/"

        var apiService: ApiService? = null

        fun geInstance(): ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }


    }

}