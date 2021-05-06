package co.yuganka.bernoulli.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * An annotation that specifies the permission request code to be used by a method which has at least one
 * RequiresPermission annotation with a PermissionDisabledPolicy of PermissionDisabledPolicy.ASK_IF_MISSING.
 *
 * When we ask for permissions from the OS, we can pass one or many permissions at once. However, the request code is
 * one only which is correlated with the entire array of permissions to be asked. Therefore, it makes more sense to
 * have the permission request code attribute associated with a method (which may have one or more RequiresPermission
 * annotations) than with each such annotation individually.
 *
 * The method must be in a sub-class of BernoulliActivity.
 *
 * A method can apply this annotation only once.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionRequestCode {

    /**
     * The request code to be used when asking for the permission from the OS.
     */
    int value() default 1;
}