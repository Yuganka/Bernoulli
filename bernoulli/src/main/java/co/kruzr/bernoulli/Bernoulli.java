package co.kruzr.bernoulli;

import android.app.Application;
import android.content.Context;

/**
 * Daniel Bernoulli was a Swiss mathematician and physicist who came up with his famous Bernoulli's Principle in 1738.
 * <p>
 * The principle concerns characterising the flow of a fluid and says that the velocity and pressure at any point are
 * inversely related - more the pressure slower the velocity and vice versa.
 * <p>
 * This library is trying to characterise the flow of code execution in Android on similar lines.
 * <p>
 * It aims to provide a clear representation of the impediments in the flow of certain code sequences (for example,
 * requirements of certain Android permissions or settings) to help you better plan how your application should
 * proceed in the presence or absence of the same.
 * <p>
 * We can think of each class as a river and each method in the class like a stream in the river.
 * <p>
 * This is the class through which the client will interact with the library.
 */
public class Bernoulli {

    /**
     * Singleton pattern.
     */
    private static volatile Bernoulli bernoulliInstance;

    private BernoulliBank bernoulliBank;

    /**
     * Must be called in the Application class.
     */
    public static Bernoulli startFlowing(Application application) {

        if (bernoulliInstance == null)
            bernoulliInstance = new Bernoulli(application.getApplicationContext());

        return bernoulliInstance;
    }

    private Bernoulli(Context context) {
        bernoulliBank = BernoulliBank.getBank(context);
    }

    /**
     * Should be called by each distinct class where Bernoulli annotations will be used.
     *
     * @param iFlowStateEvaluator the IFlowStateEvaluator interface that the class must implement in order to use
     *                            Bernoulli annotations within itself
     */
    public void createRiver(IFlowStateEvaluator iFlowStateEvaluator) {
        bernoulliBank.addRiverBank(iFlowStateEvaluator);
    }
}
