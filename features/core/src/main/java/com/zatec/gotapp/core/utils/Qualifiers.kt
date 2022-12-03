package com.zatec.gotapp.core.utils

import javax.inject.Qualifier


/**
 * I o context
 * helps differentiate between type of coroutine context that is being injected
 * @constructor Create empty I o context
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IOContext

/**
 * Default context
 *
 * @constructor Create empty Default context
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultContext

/**
 * Main context
 *
 * @constructor Create empty Main context
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainContext