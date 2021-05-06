package co.yuganka.bernoulli;

/**
 * Covers what to do in the event of a particular Setting's state being different from what the client desires.
 * disabled.
 *
 * Please read the doc associated with PermissionDisabledPolicy.ASK_IF_MISSING.
 */
public enum SettingsStateMismatchPolicy {

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
     * More specifically, if a method has multiple RequiresSetting annotations and if even one of them has
     * set this as SettingsStateMismatchPolicy, then if setting's state is not matching the one desired, the method
     * execution will not happen, irrespective of the SettingsStateMismatchPolicy set on the other RequiresSetting
     * annotation(s) on the method.
     */
    FAIL,

    /**
     * Show a dialog telling the user that such and such setting(s) needs to be enabled or disabled.
     */
    SHOW_DIALOG;
}
