package co.kruzr.bernoulli;

import java.util.List;

import co.kruzr.bernoulli.annotation.RequiresPermission;
import co.kruzr.bernoulli.annotation.RequiresSetting;
import lombok.Getter;
import lombok.Setter;

/**
 * Model class for a given stream, which is basically a metaphor for a method.
 *
 * Encapsulates all the permission and setting requirements of the method which has been annotated with an AddStream
 * annotation with the name stored in field uniqueName.
 */
class Stream {

    /**
     * The hashcode of the instance of the IFlowStateEvaluator interface that is implemented in the class
     * where this method is defined.
     */
    @Getter
    private Integer interfaceHashcode;

    /**
     * The list of permissions that this method requires.
     */
    @Getter
    @Setter
    private List<RequiresPermission> requiredPermissions;

    /**
     * The list of settings that this method needs.
     */
    @Getter
    @Setter
    private List<RequiresSetting> requiredSettings;

    public Stream(Integer interfaceHashcode) {
        this.interfaceHashcode = interfaceHashcode;
    }
}
