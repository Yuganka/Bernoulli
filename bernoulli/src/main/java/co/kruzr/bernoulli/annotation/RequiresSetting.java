package co.kruzr.bernoulli.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import co.kruzr.bernoulli.Settings;
import co.kruzr.bernoulli.SettingsStateMismatchPolicy;
import co.kruzr.bernoulli.annotation.repeatable.SettingsRepeatable;

/***
 * An annotation that specifies a given setting requirement for a method, and the expected behaviour when its desired
 * state is different from its actual state.
 *
 * A method can apply this annotation only once.
 */
@Repeatable(SettingsRepeatable.class)
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresSetting {

    /**
     * The type of setting.
     */
    Settings setting();

    /**
     * Whether the setting is desired to be enabled or disabled.
     */
    boolean shouldBeEnabled() default true;

    /**
     * Expected behaviour if the actual setting state mismatches the one desired. If not specified, usual method execution shall
     * proceed if the setting state is different from the one desired.
     */
    SettingsStateMismatchPolicy settingsStateMismatchPolicy() default SettingsStateMismatchPolicy.PROCEED;
}