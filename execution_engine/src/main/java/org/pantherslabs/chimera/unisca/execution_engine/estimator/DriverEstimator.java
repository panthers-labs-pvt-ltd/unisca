package org.pantherslabs.chimera.unisca.execution_engine.estimator;

import org.apache.spark.sql.SparkSession;
import org.pantherslabs.chimera.unisca.logging.ChimeraLogger;
import org.pantherslabs.chimera.unisca.logging.ChimeraLoggerFactory;

import static java.lang.Math.round;

/*
This class is responsible to manage all the configuration related to driver.
 */
public class DriverEstimator extends ConfEstimator {

    private static final ChimeraLogger log = ChimeraLoggerFactory.getLogger(DriverEstimator.class);

    protected DriverEstimator(SparkSession.Builder ctxBuilder) {
        //protected builder variable initialized
        super(ctxBuilder);
        //if you want to test, validity of session and builder.
    }

    @Override
    void calculate() {
    /*
    to calculate
     */

    }

    @Override
    void setConfigValue() {
    /*
    to set the config value
     */

    }

    public static int[] estimateSparkDriverCore() {
        return  new int[3];
    }
}

