package co.kruzr.bernoulli;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

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
            "execution(@co.kruzr.bernoulli.annotations.RequiresPermission * *(..))";

    /**
     * Defines a pointcut for the annotation RequiresSetting.
     */
    private static final String POINTCUT_METHOD_SETTING =
            "execution(@co.kruzr.bernoulli.annotations.RequiresSetting * *(..))";


    @Pointcut(POINTCUT_METHOD_PERMISSION)
    public void methodAnnotatedWithRequiresPermission() {
    }

    @Pointcut(POINTCUT_METHOD_SETTING)
    public void methodAnnotatedWithRequiresSetting() {
    }

    /**
     * Whenever a method annotated with RequiresPermission or RequiresSetting is called, code execution will first
     * happen in this method from where we can control whether the rest of the method executes or not.
     * <p>
     * This method will extract the permissions and settings required by that method and evaluate the missing
     * permissions and settings and then give a callback through the appropriate interface.
     */
    @Around("methodAnnotatedWithRequiresPermission() || methodAnnotatedWithRequiresSetting()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {

        Log.e("Bernoulli", "Entered FlowAspect weaveJoinPoint");

        Object returnThis = null;

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        // Extracting all the generic interfaces implemented by the class in which this method is
        List<Type> interfaceTypes = Arrays.asList(methodSignature.getDeclaringType().getGenericInterfaces());

        if (interfaceTypes.contains(IFlowStateEvaluator.class)) {

            for (Type interfaceType : interfaceTypes) {

                Log.e("Bernoulli", "FlowAspect weaveJoinPoint " + interfaceType.toString());

                Integer interfaceHashCode = interfaceType.hashCode();

                // If the class has implemented the IFlowStateEvaluator interface
                if (interfaceType instanceof IFlowStateEvaluator) {

                    Stream stream =
                            new FlowRequirementsExtractor(methodSignature.getMethod(), interfaceHashCode).getRequirementsOfStream();

                    try {

                        if (stream != null) {

                            Log.e("Bernoulli", "FlowAspect weaveJoinPoint ");

                            // proceeds with execution of the method if all conditions are fulfilled, else doesn't
                            if (BernoulliBank.shouldProceed(interfaceHashCode, stream)) {
                                Log.e("Bernoulli", "FlowAspect weaveJoinPoint should proceed");
                                returnThis = joinPoint.proceed();
                            } else {
                                Log.e("Bernoulli", "FlowAspect weaveJoinPoint should not proceed");
                                returnThis = null;
                            }

                        } else {

                            Log.e("Bernoulli", "FlowAspect weaveJoinPoint stream is null");
                            returnThis = joinPoint.proceed();
                        }

                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                        returnThis = joinPoint.proceed();
                    }
                }
            }

        } else {

            Log.e("Bernoulli", "Cannot proceed - annotations have been used in an instance which is not of type " +
                    "IFlowStateEvaluator");

            returnThis = joinPoint.proceed();
        }

        return returnThis;
    }
}
