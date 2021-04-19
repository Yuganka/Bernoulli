package co.kruzr.bernoulli;

/**
 * Covers what to do in the event of a particular Setting being disabled.
 */
public enum SettingsDisabledPolicy {

    PROCEED,
    FAIL,

    /**
     * Show a dialog telling the user that such and such setting needs to be enabled or disabled.
     */
    SHOW_DIALOG_IF_STATE_MISMATCH;
}
