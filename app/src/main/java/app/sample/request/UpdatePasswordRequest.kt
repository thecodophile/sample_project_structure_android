package app.sample.request


import com.google.gson.annotations.SerializedName

data class UpdatePasswordRequest(
    @SerializedName("password")
    val password: String?,
    @SerializedName("confirm_password")
    val confirmPassword: String?
)