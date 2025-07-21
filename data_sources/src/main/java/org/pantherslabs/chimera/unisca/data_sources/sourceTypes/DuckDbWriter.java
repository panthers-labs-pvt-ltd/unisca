package org.pantherslabs.chimera.unisca.data_sources.sourceTypes;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.pantherslabs.chimera.unisca.data_sources.model.DataWriter;
import org.pantherslabs.chimera.unisca.logging.ChimeraLogger;
import org.pantherslabs.chimera.unisca.logging.ChimeraLoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DuckDbWriter implements DataWriter.DuckDb {

    private static final ChimeraLogger logger = ChimeraLoggerFactory.getLogger(DuckDbWriter.class);
    private final String loggerTagName = "JDBC Writer";
    /**
     * Converts a Map to a Properties object.
     */
    private Properties mapToProperties(Map<String, String> map) {
        Properties props = new Properties();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                props.setProperty(entry.getKey(), entry.getValue());
            }
        }
        return props;
    }


    /**
     * Writes a DataFrame to a DuckDB table with provided connection details and options.
     */
    @Override
    public boolean write(String inSourceType,
                         Dataset<Row> inSourceDataFrame,
                         String inUrl,
                         String inSavingMode,
                         String inTable,
                         Map<String, String> propertiesMap) {

        logger.logInfo("Initiating DuckDB write operation...");

        try {
            if (inSourceDataFrame == null || inSourceDataFrame.isEmpty()) {
                logger.logWarning("Input DataFrame is null or empty. Aborting write.");
                return false;
            }

            if (inUrl == null || inUrl.isEmpty() || inTable == null || inTable.isEmpty()) {
                logger.logWarning("DuckDB URL or table name is missing.");
                return false;
            }

            Properties props = mapToProperties(propertiesMap);

            logger.logInfo(String.format("Writing to DuckDB [%s], mode: %s, table: %s", inUrl, inSavingMode, inTable));

            inSourceDataFrame.write()
                    .mode(inSavingMode)
                    .jdbc("jdbc:duckdb:" + inUrl, inTable, props);

            logger.logInfo("Write to DuckDB completed successfully.");
            return true;

        } catch (Exception ex) {
            logger.logError("Failed to write DataFrame to DuckDB: " + ex.getMessage(), ex);
            return false;
        }
    }
}