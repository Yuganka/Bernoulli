package co.kruzr.bernoulli;

/**
 * Covers what to do in the event of a particular Permission being disabled.
 */
public enum PermissionDisabledPolicy {

    /**
     * Proceed with the execution of the method.
     *
     * This value has the lowest precedence.
     */
    PROCEED,

    /**
     * Stop the execution of the method.
     * <p>
     * This value has the highest precedence.
     * <p>
     * More specifically, if a method has multiple RequiresPermission annotations and if even one of them has set
     * this as PermissionDisabledPolicy, then if that permission is missing, the method execution will not happen,
     * irrespective of the PermissionDisabledPolicy of other RequiresPermission annotation(s) of the method.
     */
    FAIL,

    /**
     * Ask for the relevant permission in the current activity. The callback will come in
     * onRequestPermissionsResult of the same activity, and from there the client can choose the future course of
     * action (which may be to call the method again if the permission has been granted, or something else).
     * <p>
     * Important note - this value will be given the highest priority among all permission and setting requirements
     * of a method. Specifically, if any RequiresPermission annotation on a method has set this as
     * permissionDisabledPolicy AND the permission has not been granted, then the settingsStateMismatchPolicy of any
     * Settings annotation(s) applied on the method will revert to SettingsStateMismatchPolicy.FAIL.
     * <p>
     * If this is set as permissionDisabledPolicy in a RequiresPermission annotation on a method and the specified
     * permission is in granted state, then the settingsStateMismatchPolicy of any Settings annotation(s) applied on
     * the method will retain their value (and, consequently, their behaviour).
     */
    ASK_IF_MISSING;
}
