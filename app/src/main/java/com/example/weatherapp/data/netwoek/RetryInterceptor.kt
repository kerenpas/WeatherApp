package com.example.weatherapp.data.netwoek

import com.example.weatherapp.data.netwoek.annotation.Retry
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.math.pow

class RetryInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val maxRetries = request.tag(Retry::class.java)?.maxRetries ?: 3

        var retryCount = 0
        var response: Response? = null

        while (retryCount < maxRetries) {
            try {
                response = chain.proceed(request)
                if (response.isSuccessful) {
                    return response
                }
            } catch (e: IOException) {
                if (retryCount == maxRetries - 1) throw e
            }
            retryCount++
            // Exponential backoff
            Thread.sleep((2.0.pow(retryCount.toDouble()) * 1000).toLong())
        }
        return response ?: throw IOException("Request failed after $maxRetries retries")
    }
}