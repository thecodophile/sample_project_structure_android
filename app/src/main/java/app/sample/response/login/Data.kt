package app.safezonez.response.login


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("country_id")
    val countryId: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phoneNo")
    val phoneNo: String,
    @SerializedName("pinCode")
    val pinCode: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("subscriptionPlane_id")
    val subscriptionPlaneId: String,
    @SerializedName("userName")
    val userName: String,
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("profileImage")
    val profileImage: String,
    @SerializedName("__v")
    val v: String
)