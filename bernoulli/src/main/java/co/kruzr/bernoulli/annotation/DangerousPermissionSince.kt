package co.kruzr.bernoulli.annotation

import co.kruzr.bernoulli.Constants

/***
 * An annotation that specifies the API Level in which a given dangerous permission has been added i.e. needs to be
 * asked from the user at runtime.
 *
 * This should only be used on the values in the Permission enum class.
 *
 * An enum value can apply this annotation only once.
 */
@Target(AnnotationTarget.FIELD)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
internal annotation class DangerousPermissionSince(
        /**
         * The API Level in which the Permission enum, on which this annotation is applied, was added.
         *
         * Th default value is 23 since it was at that API level that runtime permissions were added in Android.
         */
        val apiLevel: Int = Constants.MIN_API_LEVEL_RUNTIME_PERMISSIONS)