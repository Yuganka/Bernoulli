package co.kruzr.bernoulli;

import android.content.Context;

/**
 * This class is akin to a bank which stores objects of importance, and can also be taken to refer to the bank of a
 * river.
 * <p>
 * Holds any and all relevant information of the client and its state (river in the Bernoulli jargon), which may be
 * required by Mr. Bernoulli from time to time.
 */
class BernoulliBank {

    /**
     * Instance of the class.
     */
    private static volatile BernoulliBank bernoulliBankInstance;

    /**
     * The client's context is needed by Mr. Bernoulli so that he can properly determine the states of
     * Android permissions and setting states of the client.
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

    /**
     * Private constructor
     * @param passedApplicationContext
     */
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
