package app.sample.request


import com.google.gson.annotations.SerializedName

data class EditProfileRequest(
    @SerializedName("name")
    val name: String?/*,
    @SerializedName("email")
    val email: String?*/,
    @SerializedName("mobile")
    val mobile: String?
)