package com.example.moviesdb.services

import com.example.moviesdb.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwODcwMzlmMGM0OGNkM2RlZGJhYTMwMjBlY2Q4YzE0YyIsInN1YiI6IjY0YWRkOTg2YjY4NmI5MDE1MDExYzkyMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.QEYmRaksNlvA8RNHzzpKrfxJwPW06mHp3eChwOYsnbY")
                    .build()
                chain.proceed(request)
            })
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.Main_Url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val simpleApi: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }

}