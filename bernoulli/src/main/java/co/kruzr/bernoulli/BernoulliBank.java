package co.kruzr.bernoulli;

import android.content.Context;

/**
 * This class is akin to a bank which stores objects of value, and can also be taken to refer to the bank of a
 * river.
 * <p>
 * Holds information of client classes (river in the Bernoulli jargon) where some methods (streams in the
 * Bernoulli jargon) are using Bernoulli annotations, along with their @IFlowStateEvaluator interfaces.
 */
class BernoulliBank {

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
     * Given a method that is using Bernoulli annotation, this calculates whether we can proceed with the
     * method's normal functioning.
     * <p>
     * If we can't, we send the onFailure callback to the relevant IFlowStateEvaluator interface.
     *
     * @param stream the method whose requirements have to be evaluated
     * @return true if we can proceed with the normal functioning of the method, false otherwise
     */
    static boolean shouldProceed(Stream stream) {

        EvaluatedStream evaluatedStream = new FlowStateEvaluator().evaluate(stream);

        return evaluatedStream.getMissingPermissions().size() == 0 && evaluatedStream.getMissingSettings().size() == 0;
    }

    /**
     * Supplies a context that the library may need under certain circumstances.
     */
    public static Context getContext() {
        return applicationContext;
    }
}
