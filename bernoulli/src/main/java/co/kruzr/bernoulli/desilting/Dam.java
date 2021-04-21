package co.kruzr.bernoulli.desilting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.kruzr.bernoulli.StreamFlowState;
import co.kruzr.bernoulli.annotation.RequiresPermission;
import co.kruzr.bernoulli.annotation.RequiresSetting;
import lombok.Getter;

/**
 * Model class for a dam which may have been constructed on a given stream, which is basically a metaphor for
 * obstructions in the execution of a method.
 * <p>
 * Encapsulates all the permissions and settings of the method (basically impediments in the flow of the stream) which
 * need to be removed, and so will be asked from the user.
 */
public class Dam {

    /**
     * Encapsulates the overall state of the stream.
     */
    @Getter
    private final StreamFlowState streamFlowState;

    /**
     * The list of permissions that need to be asked, values will only be relevant when streamFlowState is
     * StreamFlowState.CHOKED, irrelevant otherwise.
     */
    private List<RequiresPermission> askPermissions = new ArrayList<>();

    /**
     * The list of settings whose requirements needs to be told to the user, values will only be relevant when
     * streamFlowState is StreamFlowState.CHOKED, irrelevant otherwise.
     */
    private List<RequiresSetting> showSettingsRequirementDialog = new ArrayList<>();

    /**
     * If streamFlowState is either StreamFlowState.FLOW or StreamFlowState.STAGNATE, then it means the method's
     * state is clear - it should be executed in the former case and not executed in the latter case.
     *
     * In these cases, we want the askPermissions and askSettings lists to be empty and un-editable
     * from outside of this class.
     * <p>
     * So for those cases, we make them unmodifiable in the constructor itself.
     *
     * @param streamFlowState
     */
    public Dam(StreamFlowState streamFlowState) {

        this.streamFlowState = streamFlowState;

        // the lists will be empty at this point
        switch (streamFlowState) {

            case FLOW:
            case STAGNATE:
                askPermissions = Collections.unmodifiableList(askPermissions);
                showSettingsRequirementDialog = Collections.unmodifiableList(showSettingsRequirementDialog);
                break;
        }
    }

    /**
     * Returns the list of permissions that need to be asked from the user.
     *
     * @return
     */
    public List<RequiresPermission> getAskPermissions() {
        return askPermissions;
    }

    /**
     * Sets the list of permissions that need to be asked from the user. The values are only updated if
     * streamFlowState is StreamFlowState.CHOKED since in other states, the fate of the stream, i.e. method's execution,
     * is clear.
     *
     * @param askPermissions
     */
    public void setAskPermissions(List<RequiresPermission> askPermissions) {
        if (streamFlowState == StreamFlowState.CHOKED)
            this.askPermissions = askPermissions;
    }

    /**
     * Returns the list of settings whose state requirement needs to be shown to the user.
     *
     * @return
     */
    public List<RequiresSetting> getShowSettingsRequirementDialog() {
        return showSettingsRequirementDialog;
    }

    /**
     * Sets the list of settings whose states need to be shown to the user. The values are only updated if
     * streamFlowState is StreamFlowState.CHOKED since in other states, the fate of the stream, i.e. method's execution,
     * is clear.
     *
     * @param showSettingsRequirementDialog
     */
    public void setShowSettingsRequirementDialog(List<RequiresSetting> showSettingsRequirementDialog) {
        if (streamFlowState == StreamFlowState.CHOKED)
            this.showSettingsRequirementDialog = showSettingsRequirementDialog;
    }
}
