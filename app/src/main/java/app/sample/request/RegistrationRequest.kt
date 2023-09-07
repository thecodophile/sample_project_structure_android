package app.sample.request


import com.google.gson.annotations.SerializedName

data class RegistrationRequest(
    @SerializedName("confirm_password")
    val confirmPassword: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("mobile")
    val mobile: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("user_type")
    val userType: String?
)
