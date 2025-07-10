package org.pantherslabs.chimera.unisca.api_nexus.api_nexus_client.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private String code;
    private String message;
    private String field;
    private String detail;

    // Constructors, getters, setters
}
