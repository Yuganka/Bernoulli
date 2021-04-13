package co.kruzr.bernoulli;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

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
    private static final String POINTCUT_METHOD_SINGLE_PERMISSION =
            "execution(@co.kruzr.bernoulli.annotation.RequiresPermission * *(..))";

    /**
     * Defines a pointcut for the annotation RequiresSetting.
     */
    private static final String POINTCUT_METHOD_SINGLE_SETTING =
            "execution(@co.kruzr.bernoulli.annotation.RequiresSetting * *(..))";

    /**
     * Defines a pointcut for the annotation RequiresPermission.
     */
    private static final String POINTCUT_METHOD_MULTIPLE_PERMISSION =
            "execution(@co.kruzr.bernoulli.annotation.repeatable.PermissionsRepeatable * *(..))";

    /**
     * Defines a pointcut for the annotation RequiresSetting.
     */
    private static final String POINTCUT_METHOD_MULTIPLE_SETTING =
            "execution(@co.kruzr.bernoulli.annotation.repeatable.SettingsRepeatable * *(..))";


    @Pointcut(POINTCUT_METHOD_SINGLE_PERMISSION)
    public void methodWithSinglePermission() {
    }

    @Pointcut(POINTCUT_METHOD_SINGLE_SETTING)
    public void methodWithSingleSetting() {
    }

    @Pointcut(POINTCUT_METHOD_MULTIPLE_PERMISSION)
    public void methodWithMultiplePermissions() {
    }

    @Pointcut(POINTCUT_METHOD_MULTIPLE_SETTING)
    public void methodWithMultipleSettings() {
    }

    /**
     * Whenever a method annotated with RequiresPermission or RequiresSetting is called, code execution will first
     * happen in this method from where we can control whether the rest of the method executes or not.
     * <p>
     * This method will extract the permissions and settings required by that method and evaluate the missing
     * permissions and settings and then give a callback through the appropriate interface.
     */
    @Around("methodWithSinglePermission() || methodWithSingleSetting() || methodWithMultiplePermissions() || methodWithMultipleSettings()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {

        Log.e("Bernoulli", "Entered FlowAspect weaveJoinPoint");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Stream stream =
                new FlowRequirementsExtractor(methodSignature.getMethod()).getRequirementsOfStream();
        try {

            if (stream != null) {

                // proceeds with execution of the method if all conditions are fulfilled, else doesn't
                if (BernoulliBank.shouldProceed(stream)) {
                    Log.e("Bernoulli", "FlowAspect should proceed");
                    return joinPoint.proceed();
                } else {
                    Log.e("Bernoulli", "FlowAspect should not proceed");
                    return null;
                }

            } else {

                Log.e("Bernoulli", "FlowAspect stream is null");
                return joinPoint.proceed();
            }

        } catch (Throwable throwable) {
            Log.e("Bernoulli", "FlowAspect In catch");
            Log.e("Bernoulli", throwable.getMessage());
            throwable.printStackTrace();
            return joinPoint.proceed();
        }
    }
}
