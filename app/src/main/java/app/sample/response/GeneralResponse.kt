package app.sample.response


import com.google.gson.annotations.SerializedName

data class GeneralResponse(
    @SerializedName("msg")
    var msg: String,
    @SerializedName("purpose")
    var purpose: String,
    @SerializedName("status")
    var status: Int
)