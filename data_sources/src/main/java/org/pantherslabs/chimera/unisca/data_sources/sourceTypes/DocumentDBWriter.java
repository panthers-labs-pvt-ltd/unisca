package org.pantherslabs.chimera.unisca.data_sources.sourceTypes;

import org.pantherslabs.chimera.unisca.data_sources.formats.files.parquet;
import org.pantherslabs.chimera.unisca.data_sources.model.DataWriter;
import org.pantherslabs.chimera.unisca.logging.ChimeraLogger;
import org.pantherslabs.chimera.unisca.logging.ChimeraLoggerFactory;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Locale;

public class DocumentDBWriter implements DataWriter.NOSQL{

    private static final ChimeraLogger logger = ChimeraLoggerFactory.getLogger(DocumentDBWriter.class);
    private String loggerTagName = "NOSQL Writer";
    /**
     * @param inSourceType
     * @param inSparkSession
     * @param inPipelineName
     * @param inDatabaseName
     * @param inTableName
     * @param inSourceDataFrame
     * @param inOutputPath
     * @param inSavingMode
     * @param inPartitioningKeys
     * @param inSortingKeys
     * @param inDuplicationKeys
     * @param inExtraColumns
     * @param inExtraColumnsValues
     * @param inCustomConfig
     * @param inCompressionFormat
     * @return
     */
    @Override
    public Dataset<Row> write(String inSourceType, SparkSession inSparkSession, String inPipelineName,
                              String inDatabaseName, String inTableName, Dataset<Row> inSourceDataFrame,
                              String inOutputPath, String inSavingMode, String inPartitioningKeys,
                              String inSortingKeys, String inDuplicationKeys, String inExtraColumns,
                              String inExtraColumnsValues, String inCustomConfig, String inCompressionFormat) throws Exception {
        logger.logInfo("Initiating Writing....");
        Dataset<Row> dataFrame = inSparkSession.emptyDataFrame();
        String SourceType = inSourceType.toLowerCase(Locale.ROOT);
        if (SourceType.equals("mongo")) {
            dataFrame = parquet.write(inSparkSession, inPipelineName,inDatabaseName,inTableName,inSourceDataFrame,
                    inOutputPath,inCompressionFormat,inSavingMode,inPartitioningKeys,inSortingKeys,inDuplicationKeys,
                    inExtraColumns,inExtraColumnsValues,inCustomConfig) ;
        }

        else {
            logger.logError("Unsupported Format " + inSourceType);
            System.exit(1);
        }
    return dataFrame;
    }
}
