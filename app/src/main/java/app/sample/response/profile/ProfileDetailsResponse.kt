package app.sample.response.profile


import com.google.gson.annotations.SerializedName

data class ProfileDetailsResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("purpose")
    val purpose: String,
    @SerializedName("status")
    val status: Int
)