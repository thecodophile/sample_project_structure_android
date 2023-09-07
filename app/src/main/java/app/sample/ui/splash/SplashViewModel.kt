package app.sample.ui.splash

import androidx.lifecycle.*
import app.sample.repository.SplashRepository
import app.sample.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject
constructor(private val repository: SplashRepository,
            private val networkHelper: NetworkHelper,
            private val savedStateHandle: SavedStateHandle
):ViewModel() {
/*

    // cached
    private val _message = MutableLiveData<Resource<IntroductionResponse>>()
    // public
    val message: LiveData<Resource<IntroductionResponse>> = _message
*/

/*
    fun getIntroduction() = viewModelScope.launch {
        _message.postValue(Resource.loading(null))
        delay(2000L)
        if (networkHelper.isNetworkConnected()) {
            try {
                _message.value = Resource.success(
                    data = repository.getIntroduction())
            } catch (exception: Exception) {
                _message.value = Resource.error(
                    data = null,
                    message = exception.message ?: exception.localizedMessage
                )
            }
        } else {
            _message.postValue(
                Resource.error(
                    data = null,
                    message = "internetErr"))
        }
    }
*/

}