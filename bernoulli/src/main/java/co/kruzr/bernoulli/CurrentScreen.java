package co.kruzr.bernoulli;

import androidx.annotation.RequiresPermission;

import co.kruzr.bernoulli.android.BernoulliActivity;
import co.kruzr.bernoulli.annotation.RequiresSetting;
import lombok.Getter;
import lombok.Setter;

public class CurrentScreen {

    @Getter
    @Setter
    private static Integer currentActivityHashcode;

    @Getter
    @Setter
    private static BernoulliActivity currentActivity;

    @Getter
    @Setter
    private static RequiresPermission currentlyProcessingPermission;

    @Getter
    @Setter
    private static RequiresSetting currentlyProcessingSetting;
}
