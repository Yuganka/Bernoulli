package co.kruzr.bernoulli.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import co.kruzr.bernoulli.Settings;
import co.kruzr.bernoulli.SettingsStateMismatchPolicy;

/***
 * An annotation that encapsulates the setting requirements of a method, and the expected behaviour when that setting
 * is disabled.
 *
 * A method can apply this annotation only once.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresSetting {

    /**
     * The type of setting.
     */
    Settings setting();

    /**
     * Whether the setting should be enabled.
     */
    boolean shouldBeEnabled() default true;

    /**
     * Expected behaviour if the setting state mismatches the requirement.
     */
    SettingsStateMismatchPolicy settingsStateMismatchPolicy() default SettingsStateMismatchPolicy.PROCEED;
}