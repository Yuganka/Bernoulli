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
 * Encapsulates all the permission and setting of the method (basically impediments in the flow of the stream) which
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
     * The list of settings that need to be asked, values will only be relevant when streamFlowState is
     * StreamFlowState.CHOKED, irrelevant otherwise.
     */
    private List<RequiresSetting> askSettings = new ArrayList<>();

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
                askSettings = Collections.unmodifiableList(askSettings);
                break;
        }
    }

    public List<RequiresPermission> getAskPermissions() {
        return askPermissions;
    }

    public void setAskPermissions(List<RequiresPermission> askPermissions) {
        if (streamFlowState == StreamFlowState.CHOKED)
            this.askPermissions = askPermissions;
    }

    public List<RequiresSetting> getAskSettings() {
        return askSettings;
    }

    public void setAskSettings(List<RequiresSetting> askSettings) {
        if (streamFlowState == StreamFlowState.CHOKED)
            this.askSettings = askSettings;
    }
}
