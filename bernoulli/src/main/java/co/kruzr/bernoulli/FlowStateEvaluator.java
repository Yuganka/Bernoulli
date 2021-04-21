package co.kruzr.bernoulli;

import java.util.ArrayList;
import java.util.List;

import co.kruzr.bernoulli.annotation.RequiresPermission;
import co.kruzr.bernoulli.annotation.RequiresSetting;
import co.kruzr.bernoulli.desilting.Dam;
import co.kruzr.bernoulli.managers.PermissionsManager;
import co.kruzr.bernoulli.managers.SettingsManager;

/**
 * This class is responsible for evaluating the actual state of the permission and setting requirements for a given
 * Stream object (i.e. method).
 */
class FlowStateEvaluator {

    /**
     * Evaluates whether, for a given Stream, the permissions and settings it requires have been granted / enabled
     * and based on that, finds out its flow state.
     *
     * Only need to check for setting states if we don't need to ask for any permissions from the user, for if we
     * do then the SettingsStateMismatchPolicy becomes SettingsStateMismatchPolicy.FAIL
     *
     * @param stream the method for which the permissions and settings need to be checked
     * @return a Dam that encapsulates the state of flow of the stream
     */
    public Dam evaluate(Stream stream) {

        Dam dam;

        boolean shouldFlow = true;

        List<RequiresPermission> askPermissions = new ArrayList<>();
        List<RequiresSetting> showSettingsDialog = new ArrayList<>();

        PermissionsManager permissionsManager = new PermissionsManager(BernoulliBank.getContext());
        SettingsManager settingsManager = new SettingsManager(BernoulliBank.getContext());

        for (RequiresPermission permission : stream.getRequiredPermissions())

            if (!permissionsManager.isPermissionGranted(permission.permission()))

                switch (permission.permissionDisabledPolicy()) {

                    case PROCEED: // don't need to do anything
                        break;

                    case FAIL:
                        shouldFlow = false;
                        break;

                    case ASK_IF_MISSING:
                        askPermissions.add(permission);
                        break;
                }


        if (askPermissions.size() == 0)
            for (RequiresSetting setting : stream.getRequiredSettings())
                if (!settingsManager.isSettingsStateMatching(setting))

                    switch (setting.settingsStateMismatchPolicy()) {

                        case PROCEED: // don't need to do anything
                            break;

                        case FAIL:
                            shouldFlow = false;
                            break;

                        case SHOW_DIALOG_IF_STATE_MISMATCH:
                            showSettingsDialog.add(setting);
                            break;
                    }

        if (askPermissions.size() > 0 || showSettingsDialog.size() > 0) {
            dam = new Dam(StreamFlowState.CHOKED);
            dam.setAskPermissions(askPermissions);
            dam.setShowSettingsRequirementDialog(showSettingsDialog);
        } else
            dam = new Dam(shouldFlow ? StreamFlowState.FLOW : StreamFlowState.STAGNATE);

        return dam;
    }
}