package co.kruzr.bernoulli.annotation

/***
 * An annotation that encapsulates the setting requirements of a method, and the expected behaviour when that setting
 * is disabled.
 *
 * A method can apply this annotation only once.
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
internal annotation class AttachScreen(
        /**
         * The type of setting.
         */
        val isActive: Boolean = false)