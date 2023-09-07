package app.sample.utils.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class GenericResponse<T> : Serializable {

    @Expose
    @SerializedName("status")
    val status: Int = 0

//    @Expose
//    @SerializedName("errorCode")
//    var code: Int = 0

    @Expose
    @SerializedName("msg")
    var message: String? = null

    @Expose
    @SerializedName("data")
    val data: T? = null


}