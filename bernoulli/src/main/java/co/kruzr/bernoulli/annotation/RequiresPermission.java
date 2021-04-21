package co.kruzr.bernoulli.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import co.kruzr.bernoulli.PermissionDisabledPolicy;
import co.kruzr.bernoulli.Permission;
import co.kruzr.bernoulli.annotation.repeatable.PermissionsRepeatable;

/***
 * An annotation that specifies a given permission requirement of a method, and the expected behaviour in the
 * absence of the permission.
 *
 * A method can apply this annotation only once.
 */
@Repeatable(PermissionsRepeatable.class)
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermission {

    /**
     * The type of permission.
     */
    Permission permission();

    /**
     * Expected behaviour if the permission has not been granted. If not specified, usual method execution shall
     * proceed if permission has not been granted.
     */
    PermissionDisabledPolicy permissionDisabledPolicy() default PermissionDisabledPolicy.PROCEED;
}