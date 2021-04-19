package co.kruzr.bernoulli;

/**
 * Covers what to do in the event of a particular Setting being disabled.
 *
 * Please read the doc associated with ${PermissionDisabledPolicy.ASK_IF_MISSING}.
 */
public enum SettingsStateMismatchPolicy {

    PROCEED,
    FAIL,

    /**
     * Show a dialog telling the user that such and such setting needs to be enabled or disabled.
     */
    SHOW_DIALOG_IF_STATE_MISMATCH;
}
