package org.pantherslabs.chimera.unisca.pipeline_metadata_api.consumer;

import java.io.IOException;

import org.pantherslabs.chimera.unisca.pipeline_metadata_api.consumer.DBAPIClient;
import org.junit.jupiter.api.Test;


import com.fasterxml.jackson.core.type.TypeReference;
import org.pantherslabs.chimera.unisca.pipeline_metadata_api.dto.DataSources;

public class testDBAPIClient {
     @Test
    public void testApiCall() throws IOException, InterruptedException {
        System.out.println("calling the rest API");
        DBAPIClient dbClient = new DBAPIClient();
        System.out.println("calling the rest API");
        DataSources dataSource = dbClient.get("http://localhost:8080/api/v1/dataSources/NoSql/Mongodb_test_API1", new TypeReference<DataSources>() {});
        System.out.println(dataSource.getDataSourceType());
        
       


    }

}
