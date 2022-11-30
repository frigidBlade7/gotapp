package com.zatec.gotapp.core.utils

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IOContext

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultContext

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainContext