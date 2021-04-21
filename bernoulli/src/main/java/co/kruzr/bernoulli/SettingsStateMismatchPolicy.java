package co.kruzr.bernoulli;

/**
 * Covers what to do in the event of a particular Setting's state being different from what the client desires.
 * disabled.
 *
 * Please read the doc associated with PermissionDisabledPolicy.ASK_IF_MISSING.
 */
public enum SettingsStateMismatchPolicy {

    /**
     * Proceed with the execution of the method.
     */
    PROCEED,

    /**
     * Stop the execution of the method.
     */
    FAIL,

    /**
     * Show a dialog telling the user that such and such setting needs to be enabled or disabled.
     */
    SHOW_DIALOG_IF_STATE_MISMATCH;
}
