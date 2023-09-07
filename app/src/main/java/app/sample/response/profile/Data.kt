package app.sample.response.profile


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("country_id")
    val countryId: CountryId,
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("lastName")
    val lastName: String,
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
    @SerializedName("__v")
    val v: Int
)