package app.sample.response.contactus


import com.google.gson.annotations.SerializedName

data class AdminContact(
    @SerializedName("mobile")
    var mobile: String,
    @SerializedName("bank_account_number")
    var bankAccountNumber: String,
    @SerializedName("airtel_money")
    var airtelMoney: String,
    @SerializedName("tnm_pay_number")
    var tnmPayNumber: String,
    @SerializedName("address")
    var address: String,
    @SerializedName("contact_no")
    var contactNo: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("id")
    var id: Int
)