package co.kruzr.bernoulli;

import android.content.Context;

/**
 * This class is akin to a bank which stores objects of value, and can also be taken to refer to the bank of a
 * river.
 * <p>
 * Holds information of client classes (river in the Bernoulli jargon) where some methods (streams in the
 * Bernoulli jargon) are using Bernoulli annotations, along with their @IFlowStateEvaluator interfaces.
 */
public class BernoulliBank {

    /**
     * Instance of the class.
     */
    private static volatile BernoulliBank bernoulliBankInstance;

    /**
     * A context is needed by the library for determining the states of Android permissions and settings.
     */
    private static Context applicationContext;

    /**
     * Singleton pattern.
     */
    public static BernoulliBank setContext(Context applicationContext) {

        if (bernoulliBankInstance == null)
            bernoulliBankInstance = new BernoulliBank(applicationContext);

        return bernoulliBankInstance;
    }

    private BernoulliBank(Context passedApplicationContext) {
        applicationContext = passedApplicationContext;
    }

    /**
     * Supplies a context that the library may need under certain circumstances.
     */
    public static Context getContext() {
        return applicationContext;
    }
}
