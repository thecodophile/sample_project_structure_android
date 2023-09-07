package app.sample.request


import com.google.gson.annotations.SerializedName

data class TransferToFriendRequest(
    @SerializedName("friend_wallet_id")
    val friend_wallet_id: String?,
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("fee_amount")
    val feeAmount: Double?
)