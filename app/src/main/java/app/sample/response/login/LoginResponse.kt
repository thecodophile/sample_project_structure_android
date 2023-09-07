package app.safezonez.response.login


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("purpose")
    val purpose: String,
    @SerializedName("status")
    val status: Int
)