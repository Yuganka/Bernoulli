package co.kruzr.bernoulli;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import co.kruzr.bernoulli.android.BernoulliActivity;
import co.kruzr.bernoulli.annotation.AttachScreen;
import co.kruzr.bernoulli.annotation.PermissionRequestCode;
import co.kruzr.bernoulli.annotation.RequiresPermission;
import co.kruzr.bernoulli.annotation.RequiresSetting;
import co.kruzr.bernoulli.annotation.repeatable.PermissionsRepeatable;
import co.kruzr.bernoulli.annotation.repeatable.SettingsRepeatable;
import co.kruzr.bernoulli.desilting.AskPermissionSet;
import co.kruzr.bernoulli.desilting.AskSettingSet;
import co.kruzr.bernoulli.desilting.Dam;

/**
 * Core implementation of the Aspect Oriented Programming paradigm that we are following.
 * <p>
 * Basically, all the magic happens here.
 */
@Aspect
public class FlowAspect {

    /**
     * Defines a pointcut for the annotation RequiresPermission.
     * <p>
     * This string is basically an instruction to capture all instances where a method annotated with
     * RequiresPermission is executed.
     */
    private static final String POINTCUT_METHOD_PERMISSION =
            "execution(@co.kruzr.bernoulli.annotation.RequiresPermission * *(..))";


    /**
     * Defines a pointcut for the annotation PermissionRequestCode.
     * <p>
     * This string is basically an instruction to capture all instances where a method annotated with
     * PermissionRequestCode is executed.
     */
    private static final String POINTCUT_METHOD_PERMISSION_REQUEST_CODE =
            "execution(@co.kruzr.bernoulli.annotation.PermissionRequestCode * *(..))";


    /**
     * Defines a pointcut for the annotation RequiresSetting.
     * <p>
     * This string is basically an instruction to capture all instances where a method annotated with
     * RequiresSetting is executed.
     */
    private static final String POINTCUT_METHOD_SETTING =
            "execution(@co.kruzr.bernoulli.annotation.RequiresSetting * *(..))";


 /*   *//**
     * Defines a pointcut for the annotation RequiresPermission.
     *//*
    private static final String POINTCUT_METHOD_MULTIPLE_PERMISSION =
            "execution(@co.kruzr.bernoulli.annotation.repeatable.PermissionsRepeatable * *(..))";

    *//**
     * Defines a pointcut for the annotation RequiresSetting.
     *//*
    private static final String POINTCUT_METHOD_MULTIPLE_SETTING =
            "execution(@co.kruzr.bernoulli.annotation.repeatable.SettingsRepeatable * *(..))";
*/

    /**
     * Defines a pointcut for the annotation AttachScreen.
     * <p>
     * This string is basically an instruction to capture all instances where a method annotated with
     * AttachScreen is executed.
     */
    private static final String POINTCUT_METHOD_ATTACH_SCREEN =
            "execution(@co.kruzr.bernoulli.annotation.AttachScreen * *(..))";


    /**
     * The instances of methods with a RequiresPermission annotation are, in a sense, directed to this method.
     * <p>
     * This is carried out based on the parameter passed in the @Pointcut annotation that has been applied.
     */
    @Pointcut(POINTCUT_METHOD_PERMISSION)
    private void methodAnnotatedWithRequiresPermission() {
    }


    /**
     * The instances of methods with a PermissionRequestCode annotation are, in a sense, directed to this method.
     * <p>
     * This is carried out based on the parameter passed in the @Pointcut annotation that has been applied.
     */
    @Pointcut(POINTCUT_METHOD_PERMISSION_REQUEST_CODE)
    private void methodAnnotatedWithPermissionRequestCode() {
    }


    /**
     * The instances of methods with a RequiresSetting annotation are, in a sense, directed to this method.
     * <p>
     * This is carried out based on the parameter passed in the @Pointcut annotation that has been applied.
     */
    @Pointcut(POINTCUT_METHOD_SETTING)
    private void methodAnnotatedWithRequiresSetting() {
    }


    /**
     * The instances of methods with a AttachScreen annotation are, in a sense, directed to this method.
     * <p>
     * This is carried out based on the parameter passed in the @Pointcut annotation that has been applied.
     */
    @Pointcut(POINTCUT_METHOD_ATTACH_SCREEN)
    private void methodAnnotatedWithAttachScreen() {
    }


 /*   @Pointcut(POINTCUT_METHOD_MULTIPLE_PERMISSION)
    public void methodWithMultiplePermissions() {
    }

    @Pointcut(POINTCUT_METHOD_MULTIPLE_SETTING)
    public void methodWithMultipleSettings() {
    }*/

    /**
     * Whenever a method is executed which is annotated with any of the annotations whose pointcuts have been defined
     * above, code execution will be done "Around" this method, since we are using the @Around annotation.
     * <p>
     * This means, the control will first come to this method, then we can decide whether the method's actual code
     * should be executed or not.
     *
     * @return joinPoint.proceed() if we want the annotated method's code to be executed, null if we don't want it
     * to be executed.
     */
    @Around("methodAnnotatedWithRequiresPermission() " +
            " || methodAnnotatedWithRequiresSetting() " +
            " || methodAnnotatedWithAttachScreen() " +
            " || methodAnnotatedWithPermissionRequestCode() ")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {

        boolean methodShouldExecute = true;

        boolean arePermsAndSettingsProcessed = false;

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Log.e("Bernoulli", "Entered FlowAspect for " + methodSignature.getMethod().getName());

        List<Annotation> annotationList = Arrays.asList(methodSignature.getMethod().getDeclaredAnnotations());

        PrintUtils.printMethodAnnotations(annotationList);

        for (Annotation annotation : annotationList) {

            // don't want to return from here as we want the other annotations to also be processed
            if (annotation.annotationType() == AttachScreen.class) {
                doAttachActivityWork(methodSignature);
            }

            // in the case of RequiresPermission and RequiresSetting, the evaluation is done together. Therefore,
            // should break from the loop whenever either of these annotations is found
            else if (annotation.annotationType() == RequiresPermission.class
                    || annotation.annotationType() == RequiresSetting.class
                    || annotation.annotationType() == PermissionsRepeatable.class
                    || annotation.annotationType() == SettingsRepeatable.class) {

                if (!arePermsAndSettingsProcessed) {

                    methodShouldExecute = doRequiresSettingAndPermissionWork(methodSignature, joinPoint) != null;
                    arePermsAndSettingsProcessed = true;
                }
            }
        }

        if (methodShouldExecute)
            return joinPoint.proceed();
        else
            return null;
    }


    /**
     * This method will extract the permissions and settings required by that method and evaluate the missing
     * permissions and settings and then give a callback through the appropriate interface.
     *
     * @param methodSignature the method whose permission and setting requirements need to be extracted.
     * @param joinPoint       an object that represents the control of flow in the method which has been annotated
     * @return joinPoint.proceed() if we want the annotated method's code to be executed, null otherwise.
     * @throws Throwable
     */
    private Object doRequiresSettingAndPermissionWork(MethodSignature methodSignature, ProceedingJoinPoint joinPoint) throws Throwable {

        Log.e("Bernoulli", "doRequiresSettingAndPermissionWork");

        Stream stream =
                new FlowRequirementsExtractor(methodSignature.getMethod()).getPermissionAndSettingRequirementsOfStream();
        try {

            if (stream != null) {

                Dam dam = new FlowStateEvaluator().evaluate(stream, getPermissionRequestCode(methodSignature.getMethod()));

                Log.e("Bernoulli", "FlowAspect Stream " + dam.getStreamFlowState());

                switch (dam.getStreamFlowState()) {

                    case STAGNATE: // don't proceed with execution
                        return null;

                    case CHOKED:
                        Log.e("Bernoulli",
                                "Perm - " + dam.getAskPermissions().size() + "; Setting - " + dam.getShowSettingsRequirementDialog().size());
                        if (dam.getAskPermissions().size() > 0)
                            new AskPermissionSet(dam).begin();
                        else
                            new AskSettingSet(dam.getShowSettingsRequirementDialog()).begin();
                        return null;

                    case FLOW: // proceed with execution
                    default:
                        return joinPoint.proceed();
                }

            } else {

                Log.e("Bernoulli", "FlowAspect Stream null, proceeding");
                return joinPoint.proceed();
            }

        } catch (Throwable throwable) {
            Log.e("Bernoulli", "FlowAspect In catch " + throwable.getMessage());
            throwable.printStackTrace();
            return joinPoint.proceed();
        }
    }

    /**
     * This method (figuratively) attaches / detaches the current activity based on its lifecycle methods. This is to
     * ensure that if we do need to ask for permissions, the onRequestPermissionsResult callback comes to the
     * appropriate activity.
     *
     * @param methodSignature the method on which AttachScreen annotation has been used.
     **/
    private void doAttachActivityWork(MethodSignature methodSignature) {

        Log.e("Bernoulli", "doAttachActivityWork");

        if (methodSignature.getMethod().getDeclaringClass() == BernoulliActivity.class) {

            boolean hasEntered = new FlowRequirementsExtractor(methodSignature.getMethod()).hasEnteredActivity();

            Log.e("Bernoulli", "AttachActivity hasEntered " + hasEntered);

          /*  if (hasEntered) {
                CurrentScreen.setCurrentActivityHashcode(methodSignature.getClass().hashCode());
            } else {
                CurrentScreen.setCurrentActivityHashcode(null);
            }*/
        } else
            Log.e("Bernoulli", "Unexpected state - AttachActivity annotation can only be applied to a " +
                    "sub-class of BernoulliActivity");
    }

    // helper methods

    private Integer getPermissionRequestCode(Method method) {

        for (Annotation annotation : method.getAnnotations())
            if (annotation.annotationType() == PermissionRequestCode.class)
                return ((PermissionRequestCode) annotation).value();

        return null;
    }
}
