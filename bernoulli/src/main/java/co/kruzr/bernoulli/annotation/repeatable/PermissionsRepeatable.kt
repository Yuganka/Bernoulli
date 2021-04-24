package co.kruzr.bernoulli.annotation.repeatable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import co.kruzr.bernoulli.annotation.RequiresPermission;

/**
 * Since a given method may require multiple permissions, we need to set a meta-annotation @Repeatable to
 * RequiresPermission. That requires an intermediary class to be implemented.
 *
 * This is that class.
 */
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionsRepeatable {
    RequiresPermission[] value();
}
