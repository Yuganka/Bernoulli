package co.kruzr.bernoulli

import co.kruzr.bernoulli.android.BernoulliActivity
import co.kruzr.bernoulli.annotation.RequiresPermission
import co.kruzr.bernoulli.annotation.RequiresSetting
import lombok.Getter
import lombok.Setter

internal object CurrentScreen {

    @Getter
    @Setter
    var currentActivityHashcode: Int? = null

    @Getter
    @Setter
    var currentActivity: BernoulliActivity? = null

    @Getter
    @Setter
    var currentlyProcessingPermission: RequiresPermission? = null

    @Getter
    @Setter
    var currentlyProcessingSetting: RequiresSetting? = null
}