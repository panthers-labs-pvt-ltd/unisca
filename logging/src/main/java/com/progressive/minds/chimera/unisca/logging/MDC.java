package com.progressive.minds.chimera.unisca.logging;

import com.progressive.minds.chimera.unisca.tags.annotation.Private;
import com.progressive.minds.chimera.unisca.logging.LogKey.LogKey;

/**
 * Represents a key-value pair of LogKey and Object that can be used to get MDC (Mapped Diagnostic Context).
 * @param key
 * @param value
 */

@Private
public record MDC(LogKey key, Object value) {
    public MDC {
        if (value instanceof MessageWithContext) {
            throw new IllegalArgumentException("the class of value cannot be MessageWithContext");
        }
    }

    public static MDC of(LogKey key, Object value) {
        return new MDC(key, value);
    }
}
