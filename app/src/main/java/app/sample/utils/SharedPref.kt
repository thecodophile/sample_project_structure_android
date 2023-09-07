package app.sample.utils

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys.AES256_GCM_SPEC
import androidx.security.crypto.MasterKeys.getOrCreate
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPref @Inject constructor(@ApplicationContext context: Context) {

    private val masterKeyAlias = getOrCreate(AES256_GCM_SPEC)
    private val sharedPreferences = EncryptedSharedPreferences.create(
        PREFERENCE_NAME,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    /**
     * puts a value for the given [key].
     */
    @Synchronized
    fun saveData(key: String?, value: Any?) {
        val prefsEditor = sharedPreferences.edit()
        when (value) {
            is String? ->  { prefsEditor.putString(key, value) }
            is Int ->  { prefsEditor.putInt(key, value) }
            is Boolean ->  { prefsEditor.putBoolean(key, value) }
            is Float ->  { prefsEditor.putFloat(key, value) }
            is Long ->  { prefsEditor.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
        prefsEditor.apply()
    }


    @Synchronized
    fun getData(key: String?,defaultValue: Any?): Any? {

        return when (defaultValue) {
            is String? ->  {
                sharedPreferences.getString(key, defaultValue)
            }
            is Int ->  {
                sharedPreferences.getInt(key, defaultValue)
            }
            is Boolean ->  {
                sharedPreferences.getBoolean(key, defaultValue)
            }
            is Float ->  {
                sharedPreferences.getFloat(key, defaultValue)
            }
            is Long ->  {
                sharedPreferences.getLong(key, defaultValue)
            }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }



    @Synchronized
    fun clearData(key: String?) {
        sharedPreferences.edit()?.remove(key)?.apply()
    }


    @Synchronized
    fun clearPreference()
    {
        val editor = sharedPreferences.edit()
        editor?.clear()
        editor?.apply()
    }

    companion object {
        const val PREFERENCE_NAME = "samplePref"
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
        const val REFRESH_TOKEN = "REFRESH_TOKEN"
        const val USER_TYPE = "USER_TYPE"
        const val BUSINESS_STATUS = "BUSINESS_STATUS"
        const val LOGGED_IN_USER_DATA = "LOGGED_IN_USER_DATA"
        const val IsLogin = "isLogin"
        const val IsSocialLogin = "isSocialLogin"
        const val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"
        const val EMAIL = "email"
        const val FULL_NAME = "full_name"
        const val ID = "id"
        const val MOBILE_NO = "mobile_no"
        const val PROFILE_IMAGE = "profile_image"
        const val WALLET_ID = "wallet_id"
        const val ENDPOINT = "endpoint"
        const val currencyCode = "currencyCode"
        const val currencySymbol = "currencySymbol"
        const val planLength = "planLength"
        const val plansPrice = "plansPrice"
        const val songId = "songId"
        const val type = "type"

        private var preference: SharedPref? = null

    }

}