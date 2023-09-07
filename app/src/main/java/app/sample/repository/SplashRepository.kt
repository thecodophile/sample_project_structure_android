package app.sample.repository

import app.sample.di.RetrofitServiceWithoutHeader
import app.sample.utils.retrofit.Apis
import javax.inject.Inject

class SplashRepository @Inject constructor(@RetrofitServiceWithoutHeader private val apiClient: Apis){

//    suspend fun getIntroduction() = apiClient.getIntroduction()
}