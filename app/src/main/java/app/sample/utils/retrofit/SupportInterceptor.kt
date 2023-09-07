package app.sample.utils.retrofit

import app.sample.app.MyApplication
import app.sample.utils.SharedPref
import okhttp3.*
import java.io.IOException

class SupportInterceptor : Interceptor, Authenticator {

    /**
     * Interceptor class for setting of the headers for every request
     */
    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()
        request = request.newBuilder()
            .header(
                "Authorization",
                "Bearer ${
                    SharedPref(MyApplication.instance!!).getData(
                        SharedPref.ACCESS_TOKEN,
                        ""
                    )
                }"
            ).header("device_type","1")
            .header("Accept", "application/json")
            .header("Content-Type", "application/x-www-form-urlencoded")
            .build()
        return chain.proceed(request)
    }

    /**
     * Authenticator for when the authToken need to be refresh and updated
     * everytime we get a 401 error code
     */
    @Throws(IOException::class)
    override fun authenticate(route: Route?, response: Response): Request? {


        /**
         * Refresh token request
         * */
        val request = mapOf(
            "refreshtoken" to "${
                SharedPref(MyApplication.instance!!).getData(
                    SharedPref.REFRESH_TOKEN,
                    ""
                )
            }"
        )


        /**
         * Refresh token api called inside Couroutine and on response accesstoken and refreshtoken are being saved in Sharedpreference
         * and the previous 401 api is being called
         * */
        return response.request.newBuilder()
            .header(
                "Authorization", "${
                    SharedPref(MyApplication.instance!!).getData(
                        SharedPref.ACCESS_TOKEN, ""
                    )
                }"
            )
            .build()
    }

}