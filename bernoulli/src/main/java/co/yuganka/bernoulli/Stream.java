package co.yuganka.bernoulli;

import java.util.List;

import co.yuganka.bernoulli.annotation.RequiresPermission;
import co.yuganka.bernoulli.annotation.RequiresSetting;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model class for a given stream, which is basically a metaphor for a method.
 *
 * Encapsulates all the permission and setting requirements of the method.
 */
@NoArgsConstructor
class Stream {

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
}
