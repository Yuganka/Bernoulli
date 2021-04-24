package co.kruzr.bernoulli.annotation.repeatable

import co.kruzr.bernoulli.annotation.RequiresPermission
import java.lang.annotation.Inherited

/**
 * Since a given method may require multiple permissions, we need to set a meta-annotation @Repeatable to
 * RequiresPermission. That requires an intermediary class to be implemented.
 *
 * This is that class.
 */
@Inherited
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
internal annotation class PermissionsRepeatable(

        /**
         * Signifies that multiple such annotations can be applied
         */
        vararg val value: RequiresPermission
)