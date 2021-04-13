package co.kruzr.bernoulli.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Any method using a Bernoulli annotation *MUST* also apply this annotation.
 *
 * This is supposed to act as a human-readable method signature/id of that method.
 *
 * When any permission or setting required by a method is missing, the callback to the class shall return the withName
 * field for the client to use for further processing, for instance using a switch-case to specify the necessary actions
 * based on the method.
 *
 * A method can apply this annotation only once.
 *
 * This annotation will be ignored if it is the only annotation that has been applied to a method.
 */
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AddStream {

    String withName() default "";
}