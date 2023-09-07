package app.sample.response.contactus


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("admin_contact")
    var adminContact: AdminContact
)