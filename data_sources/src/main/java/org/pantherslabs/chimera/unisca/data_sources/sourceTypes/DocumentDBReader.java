package org.pantherslabs.chimera.unisca.data_sources.sourceTypes;

import org.pantherslabs.chimera.unisca.data_sources.formats.nosql.nosql;
import org.pantherslabs.chimera.unisca.data_sources.model.DataReader;
import org.pantherslabs.chimera.unisca.logging.ChimeraLogger;
import org.pantherslabs.chimera.unisca.logging.ChimeraLoggerFactory;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Locale;

public class DocumentDBReader implements DataReader.NOSQL {

    private static final ChimeraLogger logger = ChimeraLoggerFactory.getLogger(DocumentDBReader.class);
    private String loggerTagName = "File Reader";

    /**
     *
     * @param inSourceType
     * @param inSparkSession
     * @param inUrl
     * @param inCollectionNm
     * @param inCustomConf
     * @return
     * @throws Exception
     */
    @Override
    public Dataset<Row> read(String inSourceType, SparkSession inSparkSession, String inUrl,
                                          String inCollectionNm, String inCustomConf) throws Exception {

        logger.logInfo("Initiating Reading....");
        Dataset<Row> dataFrame = inSparkSession.emptyDataFrame();

        String SourceType = inSourceType.toLowerCase(Locale.ROOT);
        if (SourceType.equals("mongo")) {
            dataFrame = nosql.read( inSourceType,  inSparkSession,  inUrl,
                     inCollectionNm,  inCustomConf);
        }
        else {
            logger.logError("Unsupported Format " + inSourceType);
            System.exit(1);
        }
        return dataFrame;
    }
}