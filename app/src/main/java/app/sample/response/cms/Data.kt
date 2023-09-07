package app.sample.response.cms


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("content")
    var content: String,
    @SerializedName("createdAt")
    var createdAt: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("status")
    var status: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("updatedAt")
    var updatedAt: String
)