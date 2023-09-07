package app.sample.response.registration


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("access_token")
    var accessToken: String,
    @SerializedName("createdAt")
    var createdAt: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("mobile")
    var mobile: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("refresh_token")
    var refreshToken: String,
    @SerializedName("updatedAt")
    var updatedAt: String,
    @SerializedName("user_type")
    var userType: String
)