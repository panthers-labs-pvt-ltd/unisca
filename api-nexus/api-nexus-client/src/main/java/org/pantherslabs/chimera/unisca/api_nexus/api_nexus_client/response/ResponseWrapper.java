package org.pantherslabs.chimera.unisca.api_nexus.api_nexus_client.response;

import lombok.Getter;

@Getter
public class ResponseWrapper<T, F> {
    private final T successBody;
    private final F failureBody;
    private final int statusCode;

    public ResponseWrapper(T successBody, F failureBody, int statusCode) {
        this.successBody = successBody;
        this.failureBody = failureBody;
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return statusCode >= 200 && statusCode < 300;
    }
}
