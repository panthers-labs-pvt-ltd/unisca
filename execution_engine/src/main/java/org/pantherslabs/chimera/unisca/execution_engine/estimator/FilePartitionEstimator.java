package org.pantherslabs.chimera.unisca.execution_engine.estimator;

import org.apache.spark.sql.SparkSession;
import org.pantherslabs.chimera.unisca.logging.ChimeraLogger;
import org.pantherslabs.chimera.unisca.logging.ChimeraLoggerFactory;

/*

The number of partitions decided in the input RDD/Dataset could affect
the efficiency of the entire execution pipeline of the Job. Hence, it is important to set things right.
This class in essence, optimizes configurations as used in
org.apache.spark.sql.execution.datasources.FilePartition (A collection of file blocks
that should be read as a single task)

Four configurations which decide this -
sparkSession.sessionState.conf.filesOpenCostInBytes
sparkSession.sessionState.conf.filesMaxPartitionBytes
sparkSession.sessionState.conf.filesMinPartitionNum
sparkSession.sparkContext.defaultParallelism

Implementing E-73
 */

public class FilePartitionEstimator extends ConfEstimator {
    private static final ChimeraLogger log = ChimeraLoggerFactory.getLogger(FilePartitionEstimator.class);

    protected FilePartitionEstimator(SparkSession.Builder ctxBuilder) {
        super(ctxBuilder);

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
}
