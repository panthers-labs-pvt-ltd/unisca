package org.pantherslabs.chimera.unisca.data_sources.model;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Map;

public interface DataWriter {
    interface Files {
        Dataset<Row> write(String inSourceType, SparkSession inSparkSession, String inPipelineName, String inDatabaseName,
                           String inTableName, Dataset<Row> inSourceDataFrame, String inOutputPath, String inSavingMode,
                           String inPartitioningKeys, String inSortingKeys, String inDuplicationKeys,
                           String inExtraColumns, String inExtraColumnsValues, String inCustomConfig,
                           String inCompressionFormat) throws Exception;
    }
    interface Databases {
        boolean write(String inSourceType, Dataset<Row> inSourceDataFrame,String inDataSourceNm,
                      String inDatabaseName, String inTableName, String inSaveMode, String inCustomConf) throws Exception;
    }
    interface OpenTables {
        void openTableFormatsDatawrite();
    }
    interface NOSQL {
        Dataset<Row> write(String inSourceType, SparkSession inSparkSession, String inPipelineName,
                           String inDatabaseName, String inTableName, Dataset<Row> inSourceDataFrame,
                           String inOutputPath, String inSavingMode, String inPartitioningKeys,
                           String inSortingKeys, String inDuplicationKeys, String inExtraColumns,
                           String inExtraColumnsValues, String inCustomConfig, String inCompressionFormat) throws Exception;    }

    interface DuckDb{
        boolean write(String inSourceType,
                      Dataset<Row> inSourceDataFrame,String inUrl, String inSavingMode, String inTable, Map<String, String> propertiesMap) throws Exception;
    }
}

