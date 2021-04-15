package co.kruzr.bernoulli;

/**
 * Covers what to do in the event of a particular Permission or Setting being disabled.
 */
public enum DisabledPolicy {

    PROCEED,
    FAIL,
    ASK_IF_MISSING,
    ASK_IF_MISSING_AND_SHOW_RATIONALE_IF_DENIED;
}
