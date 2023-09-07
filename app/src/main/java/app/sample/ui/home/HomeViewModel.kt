package app.sample.ui.home

import android.util.Log
import androidx.lifecycle.*
import app.sample.base.BaseViewModel
import app.sample.di.RetrofitServiceWithHeader
import app.sample.response.GeneralResponse
import app.sample.response.profile.Data
import app.sample.utils.NetworkHelper
import app.sample.utils.SharedPref
import app.sample.utils.retrofit.Apis
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject
constructor(
    @RetrofitServiceWithHeader val client: Apis,
    val pref: SharedPref,
    private val networkHelper: NetworkHelper,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _state: MutableStateFlow<HomeStates> =
        MutableStateFlow(HomeStates.Loading(false))
    val homeState = _state

    fun getProfileDetails(token: String) {
        viewModelScope.launch {
            _state.value = HomeStates.Loading(true)
            if (networkHelper.isNetworkConnected()) {
                try {
                    val response = client.fetchDetails(token)
                    _state.value = HomeStates.Loading(false)
                    if (response.status == 200) {

                        if (response.data != null) {
                            _state.value = HomeStates.Success(response.data)
                        } else {
                            _state.value =
                                HomeStates.Failure("Something went wrong. Please try again")
                        }
                    } else {
                        _state.value =
                            HomeStates.Failure(
                                response.msg ?: "Something went wrong. Please try again"
                            )
                    }
                } catch (e: HttpException) {
                    Log.e("login: error", e.message())
                    _state.value = HomeStates.Loading(false)
                    _state.value = HomeStates.Failure("Something went wrong. Please try again")

                } catch (e: Exception) {
                    Log.e("login: error", e.message.toString())
                    _state.value = HomeStates.Loading(false)
                    _state.value = HomeStates.Failure("Something went wrong. Please try again")
                }
            } else {
                _state.value = HomeStates.NoInternet
            }

        }

    }

    sealed class HomeStates {
        data class Loading(val isLoading: Boolean) : HomeStates()
        data class Success(val data: Data?) : HomeStates()
        data class Failure(val message: String) : HomeStates()
        object NoInternet : HomeStates()
    }


    private val _stateServices: MutableStateFlow<UpdateProfileStates> =
        MutableStateFlow(UpdateProfileStates.Loading(false))
    val logoutState = _stateServices
    fun userLogout(
    ) {
        viewModelScope.launch {

            _stateServices.value = UpdateProfileStates.Loading(true)
            if (networkHelper.isNetworkConnected()) {
                try {
                    val response = client.userLogout(
                    )
                    _stateServices.value = UpdateProfileStates.Loading(false)
                    if (response.status == 200) {
                        _stateServices.value = UpdateProfileStates.Success(response)
                    } else {
                        _stateServices.value =
                            UpdateProfileStates.Failure(
                                response.msg ?: "Something went wrong. Please try again"
                            )
                    }
                } catch (e: HttpException) {
                    Log.e("login: error", e.message())
                    _stateServices.value = UpdateProfileStates.Loading(false)
                    _stateServices.value =
                        UpdateProfileStates.Failure("Something went wrong. Please try again")

                } catch (e: Exception) {
                    Log.e("login: error", e.message.toString())
                    _stateServices.value = UpdateProfileStates.Loading(false)
                    _stateServices.value =
                        UpdateProfileStates.Failure("Something went wrong. Please try again")
                }
            } else {
                _stateServices.value = UpdateProfileStates.NoInternet
            }

        }

    }

    sealed class UpdateProfileStates {
        data class Loading(val isLoading: Boolean) : UpdateProfileStates()
        data class Success(val data: GeneralResponse?) : UpdateProfileStates()
        data class Failure(val message: String) : UpdateProfileStates()
        object NoInternet : UpdateProfileStates()
    }

}