package org.pantherslabs.chimera.unisca.execution_engine.estimator;

import org.apache.spark.sql.SparkSession.Builder;

public abstract class ConfEstimator {

    protected Builder builder;

    protected ConfEstimator(Builder ctxBuilder) {
        builder = ctxBuilder;
    }


    abstract void calculate();

    abstract void setConfigValue();
}