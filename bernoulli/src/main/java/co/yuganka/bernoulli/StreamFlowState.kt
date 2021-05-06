package co.yuganka.bernoulli

/**
 * Overall state of a given stream i.e. a method.
 */
internal enum class StreamFlowState {

    /**
     * The stream (method) is free to move forward as it naturally would. So, it should be executed.
     */
    FLOW,

    /**
     * The stream (method) has some restrictions and so it cannot move forward. So, it should not be executed.
     */
    STAGNATE,

    /**
     * The stream (method) has some blockages that need to be removed by asking the user. This implies that either
     * some permissions needs to be asked or a setting dialog needs to be shown to the user.
     */
    CHOKED
}