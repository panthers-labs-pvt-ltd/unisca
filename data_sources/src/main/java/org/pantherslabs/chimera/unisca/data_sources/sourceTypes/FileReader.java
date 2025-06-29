package org.pantherslabs.chimera.unisca.data_sources.sourceTypes;
import java.util.Locale;
import org.pantherslabs.chimera.unisca.data_sources.model.DataReader;
import org.pantherslabs.chimera.unisca.data_sources.formats.files.*;
import org.pantherslabs.chimera.unisca.logging.ChimeraLogger;
import org.pantherslabs.chimera.unisca.logging.ChimeraLoggerFactory;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;

public class FileReader implements DataReader.Files {

    private static final ChimeraLogger logger = ChimeraLoggerFactory.getLogger(FileReader.class);
    private String loggerTagName = "File Reader";

    /**
     * @param inSourceType
     * @param inSparkSession
     * @param inPipelineName
     * @param inSourcePath
     * @param inColumnFilter
     * @param inRowFilter
     * @param inCustomConfig
     * @param inDelimiter
     * @param inQuotes
     * @param Limit
     * @return
     */
    @Override
    public Dataset<Row> read(String inSourceType, SparkSession inSparkSession, String inPipelineName,
                             String inSourcePath, String inColumnFilter, String inRowFilter,
                             StructType inStructSchema, String inCustomConfig, String inDelimiter,
                             String inQuotes, Integer Limit) {

        logger.logInfo("Initiating Reading....");
        Dataset<Row> dataFrame = inSparkSession.emptyDataFrame();

        String SourceType = inSourceType.toLowerCase(Locale.ROOT);
        if (SourceType.equals("parquet")) {
            dataFrame = parquet.read(inSparkSession, inPipelineName,inSourcePath, inColumnFilter,  inRowFilter,
                     inCustomConfig,  Limit);
        }
        else if (SourceType.equals("json")) {
            dataFrame = parquet.read(inSparkSession, inPipelineName,inSourcePath, inColumnFilter,  inRowFilter,
                    inCustomConfig,  Limit);
        }
        else if (SourceType.equals("csv")) {
            dataFrame = parquet.read(inSparkSession, inPipelineName,inSourcePath, inColumnFilter,  inRowFilter,
                    inCustomConfig,  Limit);
        }
        else if (SourceType.equals("txt")) {
            dataFrame = parquet.read(inSparkSession, inPipelineName,inSourcePath, inColumnFilter,  inRowFilter,
                    inCustomConfig,  Limit);
        }
        else if (SourceType.equals("avro")) {
            dataFrame = parquet.read(inSparkSession, inPipelineName,inSourcePath, inColumnFilter,  inRowFilter,
                    inCustomConfig,  Limit);
        }
        else if (SourceType.equals("xml")) {
            dataFrame = parquet.read(inSparkSession, inPipelineName,inSourcePath, inColumnFilter,  inRowFilter,
                    inCustomConfig,  Limit);
        }
        else if (SourceType.equals("sequence")) {
            dataFrame = parquet.read(inSparkSession, inPipelineName,inSourcePath, inColumnFilter,  inRowFilter,
                    inCustomConfig,  Limit);
        }
        else if (SourceType.equals("binaryFile")) {
            dataFrame = parquet.read(inSparkSession, inPipelineName,inSourcePath, inColumnFilter,  inRowFilter,
                    inCustomConfig,  Limit);
        }
        else if (SourceType.equals("image")) {
            dataFrame = parquet.read(inSparkSession, inPipelineName,inSourcePath, inColumnFilter,  inRowFilter,
                    inCustomConfig,  Limit);
        }
        else {
            logger.logError("Unsupported Format " + inSourceType);
            System.exit(1);
        }
        return dataFrame;
    }
}