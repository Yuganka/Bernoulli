package co.kruzr.bernoulli.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import co.kruzr.bernoulli.DisabledPolicy;
import co.kruzr.bernoulli.Settings;
import co.kruzr.bernoulli.annotation.repeatable.SettingsRepeatable;

/***
 * An annotation that encapsulates the setting requirements of a method, and the expected behaviour when that setting
 * is disabled.
 *
 * A method can apply multiple annotations of this type.
 */
@Repeatable(SettingsRepeatable.class)
@Inherited
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
    DisabledPolicy disabledPolicy() default DisabledPolicy.PROCEED;
}