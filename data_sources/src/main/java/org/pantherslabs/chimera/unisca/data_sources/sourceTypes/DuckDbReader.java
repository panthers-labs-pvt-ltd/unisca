package org.pantherslabs.chimera.unisca.data_sources.sourceTypes;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.pantherslabs.chimera.unisca.data_sources.model.DataReader;
import org.pantherslabs.chimera.unisca.logging.ChimeraLogger;
import org.pantherslabs.chimera.unisca.logging.ChimeraLoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DuckDbReader implements DataReader.DuckDb {


    private static final ChimeraLogger logger = ChimeraLoggerFactory.getLogger(DuckDbReader.class);
    private final String loggerTagName = "DuckDbReader Reader";

    /**
     * Reads data from DuckDB using a SQL query.
     *
     * @param inSourceType    Source type for logging (optional)
     * @param inSparkSession  Spark session instance
     * @param inUrl           DuckDB database path (e.g., file:/tmp/mydb.duckdb)
     * @param sqlQuery        SQL query to execute
     * @return                Dataset<Row> containing result of the query
     * @throws Exception      If anything fails during the read
     */
    @Override
    public Dataset<Row> read(String inSourceType,
                             SparkSession inSparkSession,
                             String inUrl,
                             String sqlQuery) throws Exception {

        logger.logInfo( "Initiating DuckDB Reader...");

        try {
            if (inSparkSession == null) {
                logger.logError( "SparkSession is null. Cannot read from DuckDB.");
                throw new IllegalArgumentException("SparkSession cannot be null");
            }

            if (inUrl == null || inUrl.trim().isEmpty()) {
                logger.logError( "DuckDB URL is missing.");
                throw new IllegalArgumentException("DuckDB URL cannot be empty");
            }

            if (sqlQuery == null || sqlQuery.trim().isEmpty()) {
                logger.logError( "SQL query is missing.");
                throw new IllegalArgumentException("SQL query cannot be empty");
            }

            logger.logInfo( "Executing query: " + sqlQuery);

            Dataset<Row> result = inSparkSession.read()
                    .format("jdbc")
                    .option("url", "jdbc:duckdb:" + inUrl)
                    .option("query", sqlQuery)
                    .load();

            logger.logInfo( "Query execution successful.");
            return result;

        } catch (Exception ex) {
            logger.logError( "Error while reading from DuckDB: " + ex.getMessage(), ex);
            throw ex;
        }
    }
}