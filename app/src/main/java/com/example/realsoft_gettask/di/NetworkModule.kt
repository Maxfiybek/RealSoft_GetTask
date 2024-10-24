package com.example.realsoft_gettask.di

import com.example.realsoft_gettask.data.TasksService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    const val TOKEN = "e5fb675e-ccfb-4808-9d45-707cdad5f203"

    /**
     * Menda token borligi uchun hardcode qilib pryammoy berib ketganman siz shared preferenceda saqlab cachedan olishiz kerak
     *Shared preference inject qilish esdan chiqmasin
     * */
    @[Provides Singleton]
    fun provideOkHttpClient(
        //Siz bu yerga shared preferenceni abyektini olib kelishiz kerak
        //va tokenni shared preferencedan olib TOKEN o`rniga qo`yvorasiz
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder().apply {
                    if (TOKEN.isNotEmpty()) {
                        addHeader(
                            "Authorization",
                            "Bearer $TOKEN"
                        )
                    }
                    addHeader(
                        "accept", "application/json"
                    )
                }.build()
                chain.proceed(request)
            }
            .build()
    }

    @[Provides Singleton]
    fun provideRetrofit(
        client: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rstask.wiremockapi.cloud/")
            .addConverterFactory(converterFactory)
            .client(client)
            .build()
    }

    @[Provides Singleton]
    fun provideJsonSerializer(): Json {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = false
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideConverter(json: Json): Converter.Factory {
        return json.asConverterFactory("application/json".toMediaType())
    }

    @[Provides Singleton]
    fun provideTasksService(retrofit: Retrofit): TasksService =
        retrofit.create(TasksService::class.java)
}