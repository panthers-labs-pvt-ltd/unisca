package org.pantherslabs.chimera.unisca.data_sources.sourceTypes;

import org.pantherslabs.chimera.unisca.data_sources.formats.jdbc.jdbc;
import org.pantherslabs.chimera.unisca.data_sources.model.DataReader;
import org.pantherslabs.chimera.unisca.logging.ChimeraLogger;
import org.pantherslabs.chimera.unisca.logging.ChimeraLoggerFactory;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.HashMap;
import java.util.Map;

public class JDBCReader implements DataReader.Databases {

    private static final ChimeraLogger logger = ChimeraLoggerFactory.getLogger(JDBCReader.class);
    private final String loggerTagName = "JDBC Reader";

    @Override
    public Dataset<Row> read(String inSourceType, SparkSession inSparkSession, String inDataSourceNm,
                             String inSQLQuery, String inCustomConf) throws Exception {
        logger.logInfo("Initiating JDBC Reader....");

        Dataset<Row> dataFrame = inSparkSession.emptyDataFrame();

        // Add Logic to Pull RDS Credentials from Secret Manager or Data Source Connection Table
        // Add Logic to Pull RDS Credentials from Secret Manager or Data Source Connection Table
      //  Map<String, String> Credentials = CloudCredentials.getCredentials("", "", "");
        Map<String, String> Credentials = new HashMap<>();
                String inUserName = Credentials.getOrDefault("username" , "chimera");
        String inPassword = Credentials.getOrDefault("password" , "chimera123");
        String inJDBCUrl  = Credentials.getOrDefault("url" , "jdbc:postgresql://localhost:5432/chimera_db");

        dataFrame =  jdbc.read(inSourceType, inSparkSession,inJDBCUrl,inUserName, inPassword, inSQLQuery, inCustomConf);
        return dataFrame;
    }

    public Dataset<Row> read(String inSourceType, SparkSession inSparkSession, String inSQLQuery, String inJDBCUrl,
                             String inUserName, String inPassword, String inCustomConf) throws Exception {
        logger.logInfo("Initiating JDBC Reader....");

        return jdbc.read(inSourceType, inSparkSession,inJDBCUrl,inUserName, inPassword, inSQLQuery, inCustomConf);
    }

}