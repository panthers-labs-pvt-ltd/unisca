package org.pantherslabs.chimera.unisca.api_nexus.api_nexus_client.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

public class StandardResponse<T> {
    @Getter
    @Setter
    private boolean status;
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private T data;
    @Getter
    @Setter
    private Instant timestamp;
    private List<ApiError> errors;

    public StandardResponse(boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = Instant.now();
    }
    public StandardResponse(boolean status, String message, T data, List<ApiError> errors) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.errors = errors;
        this.timestamp = Instant.now();
    }

    public boolean getStatus() {
        return status;
    }

    public static <T> StandardResponse<T> success(T data) {
        return new StandardResponse<>(true, "Success", data);
    }

    public static <T> StandardResponse<T> success(String message, T data) {
        return new StandardResponse<>(true, message, data);
    }

    public static <T> StandardResponse<T> error(String message, List<ApiError> errors) {
        return new StandardResponse<>(false, message, null, errors);
    }

}


