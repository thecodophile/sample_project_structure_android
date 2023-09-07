package app.sample.response.profile


import com.google.gson.annotations.SerializedName

data class CountryId(
    @SerializedName("_id")
    val id: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("code")
    val code: String
)