package com.zatec.gotapp.core.api

import android.net.Uri
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        //todo regex might simplify
        val linkData = response.headers.find { it.first == "link" }?.second
        val link = linkData?.split(",")?.find { it.contains("rel=\"last\"") }?.split(";")?.first()?.replace(
            ">","")?.replace("<","")

        val pageParam = Uri.parse(link).getQueryParameter("page")
        Timber.d(pageParam)
        return response
    }
}