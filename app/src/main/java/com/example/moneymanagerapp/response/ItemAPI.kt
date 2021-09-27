package com.example.moneymanagerapp.response

import com.example.moneymanagerapp.fetchdata.GetItemResponseModel
import com.example.moneymanagerapp.request.LoginRequestModel
import com.example.moneymanagerapp.request.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ItemAPI {


    //@Headers("Accept: application/json")
        @POST("users/login")
        suspend fun login(
            @Body loginRequest: LoginRequestModel
        ): LoginResponse


        @GET("tasks")
        suspend fun getItemFromAPI(
            @Header("Authorization")
            token:String): GetItemResponseModel




        /*@POST("/v2/courses/new")
        suspend fun signUp(
            @Header("Authorization") authToken: String?,
            @Body createNewCourseRequest: CreateNewCourseRequest
        ): CreateNewCourseResponse

        @GET("app/android/version")
        suspend fun getAllTasks(
            @Header("Content-Type") contentType: String
        ): ForceUpdateAppResponseModel*/


        /*@POST("v2/data/{code}/register")
        suspend fun getPostPaymentDetails(
            @Header("Authorization") authToken: String?,
            @Path("code") code: String,
            @Body postPaymentRequestModel: PostPaymentRequestModel
        ): PostPaymentRequestModel*/


        /*@GET("/v2/videos/generate-live-token")
        suspend fun getLiveToken(
            @Header("Authorization") token: String,
            @Header("Content-Type") contentType: String,
            @Query("l_id") lessonId: String,
            @Query("user_id") userId: String,
            @Query("role") role: String
        ): GetLiveTokenResponseModel*/

    }
