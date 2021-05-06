package co.yuganka.bernoulli;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.yuganka.bernoulli.annotation.AttachScreen;
import co.yuganka.bernoulli.annotation.RequiresPermission;
import co.yuganka.bernoulli.annotation.RequiresSetting;
import co.yuganka.bernoulli.annotation.repeatable.PermissionsRepeatable;
import co.yuganka.bernoulli.annotation.repeatable.SettingsRepeatable;
import lombok.Getter;

/**
 * This class is responsible for extracting the permission and setting requirements for a
 * given Stream object (i.e. method).
 */
class FlowRequirementsExtractor {

    /**
     * The method for which the requirements have to be extracted.
     */
    private final Stream stream;


    /**
     * The list of annotations applied to the method.
     */
    private final Annotation[] annotationList;

    /**
     * The list of permissions required by the method.
     */
    @Getter
    private List<RequiresPermission> listRequiresPermissions = new ArrayList<>();

    /**
     * The list of settings and their states which are required by the method.
     */
    @Getter
    private List<RequiresSetting> listRequiresSettings = new ArrayList<>();

    /**
     * Fills in the annotationList with the annotations that have been applied to the passed method. In addition,
     * it initialises the stream object.
     *
     * @param method the method for which the requirements have to be extracted
     */
    public FlowRequirementsExtractor(Method method) {

        annotationList = method.getAnnotations();
        stream = new Stream();
    }

    /**
     * Updates the stream object with the details of the permissions and settings it needs.
     *
     * @return the updated stream object
     */
    public Stream getPermissionAndSettingRequirementsOfStream() {

        for (Annotation annotation : annotationList) {

            if (annotation instanceof RequiresPermission)
                listRequiresPermissions.add((RequiresPermission) annotation);

            if (annotation instanceof RequiresSetting)
                listRequiresSettings.add((RequiresSetting) annotation);

            if (annotation instanceof PermissionsRepeatable)
                listRequiresPermissions.addAll(Arrays.asList(((PermissionsRepeatable) annotation).value()));

            if (annotation instanceof SettingsRepeatable)
                listRequiresSettings.addAll(Arrays.asList(((SettingsRepeatable) annotation).value()));
        }

        stream.setRequiredPermissions(listRequiresPermissions);
        stream.setRequiredSettings(listRequiresSettings);

        return stream;
    }

    /**
     * Determines whether we have entered or exited the activity.
     *
     * @return true if we have entered the activity, false otherwise
     */
    public boolean hasEnteredActivity() {

        for (Annotation annotation : annotationList)
            if (annotation instanceof AttachScreen)
                return ((AttachScreen) annotation).isActive();

        return false;
    }
}