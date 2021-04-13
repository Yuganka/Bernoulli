package co.kruzr.bernoulli;

import java.util.List;

/**
 * Interface to be implemented by any class whose method(s) is/are using Bernoulli annotations.
 */
public interface IFlowStateEvaluator {

    /**
     * Callback to client to provide relevant information related with the failure of the requirements of a
     * method.
     *
     * @param missingPermissions   the list of missing permissions that impede the method's functionality
     * @param missingSettings      the list of missing settings that impede the method's functionality
     */
    void onFailure(List<Permission> missingPermissions, List<Settings> missingSettings);
}
