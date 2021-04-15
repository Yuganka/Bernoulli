package co.kruzr.bernoulli;

/**
 * Overall state of a given stream i.e. a method.
 */
public enum StreamFlowState {

    /**
     * The stream (method) is free to move forward as it naturally would.
     */
    FLOW,

    /**
     * The stream (method) has some restrictions and so it cannot move forward.
     */
    STAGNATE,

    /**
     * The stream (method) has some blockages that need to be removed by asking the user.
     */
    CHOKED;
}
