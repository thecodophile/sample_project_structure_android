package app.sample.utils.retrofit

import app.safezonez.response.login.LoginResponse
import app.sample.request.RegistrationRequest
import app.sample.response.GeneralResponse
import app.sample.response.profile.ProfileDetailsResponse
import app.sample.response.registration.RegistrationResponse
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface Apis {

    /*@POST("api/login")
    suspend fun login(@Body loginRequest: JsonObject): LoginResponse*/

    @POST("/api/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("user_name") user_name: String?,
        @Field("password") password: String?,
        @Field("fcm_token") fcm_token: String?
    ): LoginResponse

    @GET("api/fetchDetails")
    suspend fun fetchDetails(@Header("token") token: String?): ProfileDetailsResponse


    @POST("api/logout")
    suspend fun userLogout(): GeneralResponse

}