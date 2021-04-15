package co.kruzr.bernoulli.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import co.kruzr.bernoulli.Settings;
import co.kruzr.bernoulli.SettingsDisabledPolicy;

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
     * Expected behaviour if the setting is disabled.
     */
    SettingsDisabledPolicy settingsDisabledPolicy() default SettingsDisabledPolicy.PROCEED;

    /**
     * The rationale message that should be shown if specified as such in settingsDisabledPolicy.
     */
    String rationaleMessage() default "";
}