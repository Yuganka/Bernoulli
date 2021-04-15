package co.kruzr.bernoulli;

/**
 * Covers what to do in the event of a particular Permission being disabled.
 */
public enum PermissionDisabledPolicy {

    PROCEED,
    FAIL,

    IF_MISSING_THEN_ASK,

    IF_MISSING_THEN_ASK_IF_DENIED_THEN_PROCEED,

    IF_MISSING_THEN_ASK_IF_DENIED_THEN_FAIL,

    IF_MISSING_THEN_ASK_IF_DENIED_THEN_SHOW_RATIONALE,

    IF_NEVER_SHOW_AGAIN_THEN_PROCEED,

    IF_NEVER_SHOW_AGAIN_THEN_FAIL,

    IF_NEVER_SHOW_AGAIN_THEN_REDIRECT_TO_SETTINGS_AFTER_RATIONALE;
}
