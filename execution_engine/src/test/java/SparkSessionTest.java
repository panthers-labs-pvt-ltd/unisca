import org.pantherslabs.chimera.unisca.execution_engine.OptimizedSparkSession;

import org.junit.Test;

public class SparkSessionTest {

    @Test
    public void GetControl()  {
        OptimizedSparkSession spark = OptimizedSparkSession.get("DataQuality","test");
        String isHiveEnabled = spark.conf().get("spark.sql.catalogImplementation");
        System.out.println(isHiveEnabled);
        spark.sql("CREATE TABLE TEST (name String)");
        //spark.sql("CREATE TABLE test_hive_table AS SELECT 1 AS id");
        spark.sql("SHOW TABLES").show();
    }
}

