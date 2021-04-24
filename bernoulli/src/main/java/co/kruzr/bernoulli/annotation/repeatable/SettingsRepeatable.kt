package co.kruzr.bernoulli.annotation.repeatable

import co.kruzr.bernoulli.annotation.RequiresSetting
import java.lang.annotation.Inherited

/**
 * Since a given method may have multiple setting requirements, we need to set a meta-annotation @Repeatable to
 * RequiresSetting. That requires an intermediary class to be implemented.
 *
 * This is that class.
 */
@Inherited
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
internal annotation class SettingsRepeatable(

        /**
         * Signifies that multiple such annotations can be applied
         */
        vararg val value: RequiresSetting
)