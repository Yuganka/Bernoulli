package co.kruzr.bernoulli.annotation.repeatable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import co.kruzr.bernoulli.annotation.RequiresSetting;

/**
 * Since a given method may have multiple setting requirements, we need to set a meta-annotation @Repeatable to
 * RequiresSetting. That requires an intermediary class to be implemented.
 *
 * This is that class.
 */
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SettingsRepeatable {
    RequiresSetting[] value();
}
