package co.kruzr.bernoulli.annotation

/***
 * An internal annotation that ensures that whenever a sub-class of BernoulliActivity is being used, its state
 * is saved in CurrentScreen class by making the class active in it's onResume and inactive in its onPause lifecycle methods.
 *
 * A method can apply this annotation only once.
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
internal annotation class AttachScreen(
        /**
         * If the screen is active. Defaults to false.
         */
        val isActive: Boolean = false)