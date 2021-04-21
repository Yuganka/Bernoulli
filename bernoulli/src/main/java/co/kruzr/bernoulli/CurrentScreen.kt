package co.kruzr.bernoulli

import co.kruzr.bernoulli.android.BernoulliActivity
import co.kruzr.bernoulli.annotation.RequiresPermission
import co.kruzr.bernoulli.annotation.RequiresSetting
import lombok.Getter
import lombok.Setter

/**
 * Encapsulates the details of the currently active screen.
 */
internal object CurrentScreen {

    /*
     * The hashcode of the current sub-class of BernoulliActivity that is active, null otherwise.
     */
    @Getter
    @Setter
    var currentActivityHashcode: Int? = null

    /*
     * The current sub-class of BernoulliActivity that is active, null otherwise.
     */
    @Getter
    @Setter
    var currentActivity: BernoulliActivity? = null
}