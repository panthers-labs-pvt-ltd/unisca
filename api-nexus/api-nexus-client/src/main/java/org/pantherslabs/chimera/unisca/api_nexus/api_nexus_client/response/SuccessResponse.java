package org.pantherslabs.chimera.unisca.api_nexus.api_nexus_client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SuccessResponse {
    private String message;
    private String responseCode;
}

