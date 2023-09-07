package app.sample.response.registration


import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("purpose")
    var purpose: String,
    @SerializedName("status")
    var status: Int
)