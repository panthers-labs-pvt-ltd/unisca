package org.pantherslabs.chimera.unisca.api_nexus.api_nexus_client.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {
    private String errorTimestamp;
    private String errorType;
    private String errorRequestURI;
    private String errorMessage;
    private String errorCode;
}
