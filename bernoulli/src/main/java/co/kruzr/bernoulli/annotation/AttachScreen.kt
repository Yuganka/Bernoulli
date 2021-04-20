package co.kruzr.bernoulli.annotation

/***
 * An annotation that ensures the current screen's state is saved in CurrentScreen class by making the class active
 * in it's onResume and inactive in its onPause lifecycle methods.
 *
 * A method can apply this annotation only once.
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
internal annotation class AttachScreen(
        /**
         * If the screen is active.
         */
        val isActive: Boolean = false)