package app.sample.request


import com.google.gson.annotations.SerializedName

data class GetOTPForPinChangeRequest(
    @SerializedName("email")
    val email: String?
)