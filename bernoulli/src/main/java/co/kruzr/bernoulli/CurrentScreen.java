package co.kruzr.bernoulli;

import co.kruzr.bernoulli.android.BernoulliActivity;
import lombok.Getter;
import lombok.Setter;

public class CurrentScreen {

    @Getter
    @Setter
    private static Integer currentActivityHashcode;

    @Getter
    @Setter
    private static BernoulliActivity currentActivity;
}
