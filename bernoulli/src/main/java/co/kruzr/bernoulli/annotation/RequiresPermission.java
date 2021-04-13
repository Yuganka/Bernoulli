package co.kruzr.bernoulli.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import co.kruzr.bernoulli.DisabledPolicy;
import co.kruzr.bernoulli.Permission;
import co.kruzr.bernoulli.annotation.repeatable.PermissionsRepeatable;

/***
 * An annotation that encapsulates the permission requirements of a method, and the expected behaviour in the absence
 * of the permission.
 *
 * A method can apply multiple annotations of this type.
 */
@Repeatable(PermissionsRepeatable.class)
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
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