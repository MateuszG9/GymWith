package com.example.gymwith.data.remote
// fabryka połączeń sieciowych -> główny obiekt retrofgita odpowiedzialny za całą komunikację

import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object RetrofitClientFactory{
    private const val BASE_URL = "http://10.0.2.2:8000/"

    fun create(getToken: suspend () -> String?): Retrofit{
        val authInterceptor = okhttp3.Interceptor{ chain ->
            val originalRequest = chain.request()
            val token = kotlinx.coroutines.runBlocking{
                getToken()
            }

            val newRequest = if (!token.isNullOrEmpty()){
                originalRequest.newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
            } else{
                originalRequest
            }
            chain.proceed(newRequest)
        }

        val loggingInterceptor = HttpLoggingInterceptor().apply{
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

        val json = Json{
            ignoreUnknownKeys = true
            prettyPrint = true
        }

        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }
}