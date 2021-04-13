package co.kruzr.bernoulli;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.kruzr.bernoulli.android.BernoulliActivity;
import co.kruzr.bernoulli.android.BernoulliFragment;
import co.kruzr.bernoulli.annotation.RequiresPermission;
import co.kruzr.bernoulli.annotation.RequiresSetting;
import co.kruzr.bernoulli.annotation.repeatable.PermissionsRepeatable;
import co.kruzr.bernoulli.annotation.repeatable.SettingsRepeatable;
import lombok.Getter;

/**
 * This class is responsible for extracting the permission and setting requirements for a
 * given Stream object (i.e. method).
 */
class FlowRequirementsExtractor {

    /**
     * The method for which the requirements have to be extracted.
     */
    private Stream stream;

    /**
     * Whether the requirements should be extracted or not.
     * <p>
     * Its value is initialised in the constructor to true if the method also has an @AddStream annotation, and to
     * false otherwise.
     */
    private boolean shouldEvaluateRequirements;

    /**
     * The list of annotations applied to the method.
     */
    private Annotation[] annotationList;

    /**
     * The list of permissions required by the method.
     */
    @Getter
    private List<RequiresPermission> listRequiresPermissions = new ArrayList<>();

    /**
     * The list of settings required by the method.
     */
    @Getter
    private List<RequiresSetting> listRequiresSettings = new ArrayList<>();

    /**
     * Determines whether the requirements of the method should be extracted, and if yes then initialises the stream
     * object.
     *
     * @param method                      the method for which the requirements have to be extracted
     * @param hashcodeIFlowStateEvaluator hashcode of the IFlowStateEvaluator interface implemented by the class
     *                                    where this method is defined
     */
    public FlowRequirementsExtractor(Method method, Integer hashcodeIFlowStateEvaluator) {

        if (method.getDeclaringClass() == BernoulliActivity.class || method.getDeclaringClass() == BernoulliFragment.class) {

            shouldEvaluateRequirements = true;
            annotationList = method.getAnnotations();

            stream = new Stream(hashcodeIFlowStateEvaluator);

        } else
            shouldEvaluateRequirements = false;
    }

    /**
     * Updates the stream object with the details of the permissions and settings it needs.
     *
     * @return the updated stream object if the requirements should be evaluated, null otherwise
     */
    public Stream getRequirementsOfStream() {

        if (shouldEvaluateRequirements) {

            for (Annotation annotation : annotationList) {

                if (annotation instanceof PermissionsRepeatable)
                    listRequiresPermissions.addAll(Arrays.asList(((PermissionsRepeatable) annotation).value()));

                if (annotation instanceof SettingsRepeatable)
                    listRequiresSettings.addAll(Arrays.asList(((SettingsRepeatable) annotation).value()));

                if (annotation instanceof RequiresPermission)
                    listRequiresPermissions.add((RequiresPermission) annotation);

                if (annotation instanceof RequiresSetting)
                    listRequiresSettings.add((RequiresSetting) annotation);
            }

            stream.setRequiredPermissions(listRequiresPermissions);
            stream.setRequiredSettings(listRequiresSettings);

            return stream;
        }

        return null;
    }
}