package app.sample.response.contactus


import com.google.gson.annotations.SerializedName

data class AdminContactDetailsDao(
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("purpose")
    var purpose: String,
    @SerializedName("status")
    var status: Int
)