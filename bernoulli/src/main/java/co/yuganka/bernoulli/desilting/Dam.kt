package co.yuganka.bernoulli.desilting

import co.yuganka.bernoulli.StreamFlowState
import co.yuganka.bernoulli.annotation.RequiresPermission
import co.yuganka.bernoulli.annotation.RequiresSetting
import java.util.*

/**
 * Model class for a dam which may have been constructed on a given stream, which is basically a metaphor for
 * obstructions in the execution of a method.
 *
 *
 * Encapsulates all the permissions and settings of the method (basically impediments in the flow of the stream) which
 * need to be removed, and so will be asked from the user.
 */
internal class Dam(
        /**
         * Encapsulates the overall state of the stream.
         */
        val streamFlowState: StreamFlowState) {

    /**
     * The permissionRequestCode that will be used to ask for permissions associated with the method.
     */
    var permissionRequestCode: Int? = null

    /**
     * The list of permissions that need to be asked, values will only be relevant when streamFlowState is
     * StreamFlowState.CHOKED, irrelevant otherwise.
     *
     * Sets the list of permissions that need to be asked from the user. The values are only updated if
     * streamFlowState is StreamFlowState.CHOKED since in other states, the fate of the stream, i.e. method's execution,
     * is clear.
     */
    var askPermissions: List<RequiresPermission> = ArrayList()
        set(askPermissions) {
            if (streamFlowState == StreamFlowState.CHOKED) field = askPermissions
        }

    /**
     * The list of settings whose requirements needs to be told to the user, values will only be relevant when
     * streamFlowState is StreamFlowState.CHOKED, irrelevant otherwise.
     *
     * Sets the list of settings whose states need to be shown to the user. The values are only updated if
     * streamFlowState is StreamFlowState.CHOKED since in other states, the fate of the stream, i.e. method's execution,
     * is clear.
     */
    var showSettingsRequirementDialog: List<RequiresSetting> = ArrayList()
        set(showSettingsRequirementDialog) {
            if (streamFlowState == StreamFlowState.CHOKED) field = showSettingsRequirementDialog
        }


    /**
     * If streamFlowState is either StreamFlowState.FLOW or StreamFlowState.STAGNATE, then it means the method's
     * state is clear - it should be executed in the former case and not executed in the latter case.
     *
     * In these cases, we want the askPermissions and askSettings lists to be empty and un-editable
     * from outside of this class.
     *
     * So for those cases, we make them unmodifiable in the constructor itself.
     *
     */
    init {
        if (streamFlowState == StreamFlowState.FLOW || streamFlowState == StreamFlowState.STAGNATE) {
            askPermissions = Collections.unmodifiableList(askPermissions)
            showSettingsRequirementDialog = Collections.unmodifiableList(showSettingsRequirementDialog)
        }
    }
}