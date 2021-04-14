package co.kruzr.bernoulli;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import co.kruzr.bernoulli.android.BernoulliActivity;
import co.kruzr.bernoulli.annotation.AttachScreen;
import co.kruzr.bernoulli.annotation.RequiresPermission;
import co.kruzr.bernoulli.annotation.RequiresSetting;

/**
 * Core implementation of the Aspect Oriented Programming paradigm that we are following.
 * <p>
 * Basically, all the magic happens here.
 */
@Aspect
public class FlowAspect {

    /**
     * Defines a pointcut for the annotation RequiresPermission.
     */
    private static final String POINTCUT_METHOD_PERMISSION =
            "execution(@co.kruzr.bernoulli.annotation.RequiresPermission * *(..))";

    /**
     * Defines a pointcut for the annotation RequiresSetting.
     */
    private static final String POINTCUT_METHOD_SETTING =
            "execution(@co.kruzr.bernoulli.annotation.RequiresSetting * *(..))";

    /**
     * Defines a pointcut for the annotation AttachScreen.
     */
    private static final String POINTCUT_METHOD_ATTACH_SCREEN =
            "execution(@co.kruzr.bernoulli.annotation.AttachScreen * *(..))";


    @Pointcut(POINTCUT_METHOD_PERMISSION)
    private void methodAnnotatedWithRequiresPermission() {
    }

    @Pointcut(POINTCUT_METHOD_SETTING)
    private void methodAnnotatedWithRequiresSetting() {
    }

    @Pointcut(POINTCUT_METHOD_ATTACH_SCREEN)
    private void methodAnnotatedWithAttachScreen() {
    }

    /**
     * Whenever a method annotated with RequiresPermission or RequiresSetting is called, code execution will first
     * happen in this method from where we can control whether the rest of the method executes or not.
     * <p>
     * This method will extract the permissions and settings required by that method and evaluate the missing
     * permissions and settings and then give a callback through the appropriate interface.
     */
    @Around("methodAnnotatedWithRequiresPermission() " +
            " || methodAnnotatedWithRequiresSetting() " +
            " || methodAnnotatedWithAttachScreen() ")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {

        boolean methodShouldExecute = true;

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Log.e("Bernoulli", "Entered FlowAspect for " + methodSignature.getMethod().getName());

        List<Annotation> annotationList = Arrays.asList(methodSignature.getMethod().getDeclaredAnnotations());

        PrintUtils.printMethodAnnotations(annotationList);

        for (Annotation annotation : annotationList) {

            // don't want to return from here as we want the other annotations to also be processed
            if (annotation.annotationType() == AttachScreen.class) {
                doAttachActivityWork(methodSignature);
            } else if (annotation.annotationType() == RequiresPermission.class || annotation.annotationType() == RequiresSetting.class)
                methodShouldExecute &= doRequiresSettingAndPermissionWork(methodSignature, joinPoint) != null;
        }

        if (methodShouldExecute)
            return joinPoint.proceed();
        else
            return null;
    }


    // this method returning null implies the method should not be executed.
    private Object doRequiresSettingAndPermissionWork(MethodSignature methodSignature, ProceedingJoinPoint joinPoint) throws Throwable {

        Log.e("Bernoulli", "doRequiresSettingAndPermissionWork");

        Stream stream =
                new FlowRequirementsExtractor(methodSignature.getMethod()).getPermissionAndSettingRequirementsOfStream();
        try {

            if (stream != null) {

                Log.e("Bernoulli", "FlowAspect stream not null");

                // proceeds with execution of the method if all conditions are fulfilled, else doesn't
                if (BernoulliBank.shouldProceed(stream)) {
                    Log.e("Bernoulli", "FlowAspect should proceed");
                    return joinPoint.proceed();
                } else {
                    Log.e("Bernoulli", "FlowAspect should not proceed");
                    return null;
                }

            } else {

                Log.e("Bernoulli", "FlowAspect stream is null, proceeding");
                return joinPoint.proceed();
            }

        } catch (Throwable throwable) {
            Log.e("Bernoulli", "FlowAspect In catch " + throwable.getMessage());
            throwable.printStackTrace();
            return joinPoint.proceed();
        }
    }

    // this method returning null implies the method should not be executed.
    private void doAttachActivityWork(MethodSignature methodSignature) throws Throwable {

        Log.e("Bernoulli", "doAttachActivityWork");

        if (methodSignature.getMethod().getDeclaringClass() == BernoulliActivity.class) {

            boolean hasEntered = new FlowRequirementsExtractor(methodSignature.getMethod()).hasEnteredActivity();

            Log.e("Bernoulli", "AttachActivity hasEntered " + hasEntered);

            if (hasEntered) {
                CurrentScreen.setCurrentActivityHashcode(methodSignature.getClass().hashCode());
            } else {
                CurrentScreen.setCurrentActivityHashcode(null);
            }
        } else
            Log.e("Bernoulli", "Unexpected state - AttachActivity annotation can only be applied to a " +
                    "sub-class of BernoulliActivity");
    }
}
