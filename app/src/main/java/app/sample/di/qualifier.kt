package app.sample.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpClientWithHeader

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpClientWithoutHeader

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitWithHeader

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitWithoutHeader

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitServiceWithHeader

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitServiceWithoutHeader