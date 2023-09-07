package app.sample.di

import app.sample.BuildConfig
import app.sample.repository.SplashRepository
import app.sample.utils.retrofit.ApiConstants.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import app.sample.utils.retrofit.Apis
import app.sample.utils.retrofit.SupportInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/** For Reference  https://developer.android.com/training/dependency-injection/hilt-android
 *
Hilt component	             Injector for
SingletonComponent	          Application
ActivityRetainedComponent	      N/A
ViewModelComponent	           ViewModel
ActivityComponent	           Activity
FragmentComponent	           Fragment
ViewComponent	                 View
ViewWithFragmentComponent	View annotated with @WithFragmentBindings
ServiceComponent	           Service
 *
 * */


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @OkHttpClientWithHeader
    @Singleton
    @Provides
    fun provideOkHttpClientWithHeaders(): OkHttpClient =
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .followRedirects(true)
                .followSslRedirects(true)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(SupportInterceptor())
                .authenticator(SupportInterceptor())
                .build()
        }
        else {
            OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .followRedirects(true)
                .followSslRedirects(true)
                .addInterceptor(SupportInterceptor())
                .authenticator(SupportInterceptor())
                .build()
        }
    @OkHttpClientWithoutHeader
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .followRedirects(true)
                .followSslRedirects(true)
                .build()
        } else {OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .build()}


    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson = GsonBuilder().create()

    @RetrofitWithHeader
    @Singleton
    @Provides
    fun provideRetrofitWithHeader(@OkHttpClientWithHeader okHttpClient: OkHttpClient, gson: Gson):
            Retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson)).build()

    @RetrofitWithoutHeader
    @Singleton
    @Provides
    fun provideRetrofit(@OkHttpClientWithoutHeader okHttpClient: OkHttpClient, gson: Gson):
            Retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson)).build()


    @RetrofitServiceWithoutHeader
    @Singleton
    @Provides
    fun provideRetrofitService(@RetrofitWithoutHeader retrofit: Retrofit): Apis =
        retrofit.create(Apis::class.java)

    @RetrofitServiceWithHeader
    @Singleton
    @Provides
    fun provideRetrofitServiceWithHeaders(@RetrofitWithHeader retrofit: Retrofit): Apis =
        retrofit.create(Apis::class.java)

    // please refer to the below code sample if the above syntax is hard to understand

    /*
    fun provideRetrofitService(retrofit: Retrofit) : Apis
    {
        return retrofit.create(Apis::class.java)
    }
    */

    @Singleton
    @Provides
    fun provideSplashRepository(@RetrofitServiceWithoutHeader apis: Apis): SplashRepository =
        SplashRepository(apis)

}

