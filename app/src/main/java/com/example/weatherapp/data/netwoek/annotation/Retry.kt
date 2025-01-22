package com.example.weatherapp.data.netwoek.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Retry(val maxRetries: Int = 3)