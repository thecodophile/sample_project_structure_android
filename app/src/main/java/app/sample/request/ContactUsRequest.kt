package app.sample.request


import com.google.gson.annotations.SerializedName

data class ContactUsRequest(
    @SerializedName("name")
    val name: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("admin_email")
    val admin_email: String?
)