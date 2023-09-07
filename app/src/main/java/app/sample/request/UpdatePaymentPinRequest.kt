package app.sample.request


import com.google.gson.annotations.SerializedName

data class UpdatePaymentPinRequest(
    @SerializedName("otp")
    val otp: String?,
    @SerializedName("pin")
    val pin: String?,
    @SerializedName("confirm_pin")
    val confirm_pin: String?

)