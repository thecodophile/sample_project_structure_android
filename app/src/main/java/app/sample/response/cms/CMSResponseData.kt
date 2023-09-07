package app.sample.response.cms


import com.google.gson.annotations.SerializedName

data class CMSResponseData(
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("purpose")
    var purpose: String,
    @SerializedName("status")
    var status: Int
)