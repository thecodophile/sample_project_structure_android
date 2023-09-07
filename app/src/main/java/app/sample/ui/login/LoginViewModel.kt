package app.sample.ui.login

import android.text.TextUtils
import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import app.safezonez.response.login.LoginResponse
import app.sample.BR
import app.sample.base.BaseViewModel
import app.sample.di.RetrofitServiceWithoutHeader
import app.sample.utils.NetworkHelper
import app.sample.utils.SharedPref
import app.sample.utils.ValidationUtils.isValidEmail
import app.sample.utils.ValidationUtils.isValidPassword
import app.sample.utils.retrofit.Apis
import com.google.gson.Gson
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject
constructor(
    @RetrofitServiceWithoutHeader val client: Apis,
    val pref: SharedPref,
    private val networkHelper: NetworkHelper,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _event: MutableSharedFlow<LoginEvents> = MutableSharedFlow()
    val loginEvents: SharedFlow<LoginEvents> = _event

    private val _state: MutableStateFlow<LoginStates> =
        MutableStateFlow(LoginStates.Loading(false))
    val loginStates = _state


    fun login(userName: String, password: String, device_id: String) {
        val loginRequest = JsonObject()
        loginRequest.addProperty("userName", userName)
        loginRequest.addProperty("password", password)

        viewModelScope.launch {
            try {
                _state.value = LoginStates.Loading(true)
                val response = client.login(
                    userName,
                    password, "na"
                )
                if (response.status == 200) {
                    _state.value = LoginStates.Loading(false)
                    saveDataToSharedPreference(response)
                    _event.emit(LoginEvents.LoginComplete)
                } else {
                    _state.value = LoginStates.Loading(false)
                    _event.emit(
                        LoginEvents.ShowErrorMessage(
                            response.msg ?: "Something went wrong, please try again"
                        )
                    )
                }
            } catch (e: HttpException) {
                Log.e("login: error", e.message())
                _state.value = LoginStates.Loading(false)
                _event.emit(LoginEvents.ShowErrorMessage("Something went wrong, please try again"))

            } catch (e: Exception) {
                Log.e("login: error", e.message.toString())
                _state.value = LoginStates.Loading(false)
                _event.emit(LoginEvents.ShowErrorMessage("Something went wrong, please try again"))
            }

        }


    }

    sealed class LoginEvents {
        object LoginComplete : LoginEvents()
        data class ShowErrorMessage(val msg: String) : LoginEvents()
    }

    sealed class LoginStates {
        data class Loading(val isLoading: Boolean) : LoginStates()
    }


    private fun saveDataToSharedPreference(response: LoginResponse) {
        Log.e("response", "response ==> ${Gson().toJson(response)}")
        pref.saveData(SharedPref.ACCESS_TOKEN, response.data.accessToken)
        pref.saveData(SharedPref.EMAIL, response.data.email)
        pref.saveData(SharedPref.FULL_NAME, response.data.firstName + " " + response.data.lastName)
        pref.saveData(SharedPref.ID, response.data.id.toString())
        pref.saveData(SharedPref.MOBILE_NO, response.data.phoneNo)
        pref.saveData(SharedPref.PROFILE_IMAGE, response.data.profileImage)

        pref.saveData(SharedPref.IsLogin, true)
    }


    var email: String? = null
        @Bindable get
        set(email) {
            field = email
            notifyPropertyChanged(BR.email)
        }

    var password: String? = null
        @Bindable get
        set(password) {
            field = password
            notifyPropertyChanged(BR.password)
        }

    @Bindable(value = ["email", "password"])
    fun isEnabled(): Boolean {
        return !TextUtils.isEmpty(email) && isValidEmail(email) && !TextUtils.isEmpty(password) && isValidPassword(
            password
        )
    }

}