package co.kruzr.bernoulli.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import co.kruzr.bernoulli.DisabledPolicy;
import co.kruzr.bernoulli.Permission;

/***
 * An annotation that encapsulates the permission requirements of a method, and the expected behaviour in the absence
 * of the permission.
 *
 * A method can apply this annotation only once.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermission {

    /**
     * The type of permission.
     */
    Permission permission();

    /**
     * Expected behaviour if the permission has not been granted.
     */
    DisabledPolicy disabledPolicy() default DisabledPolicy.PROCEED;
}