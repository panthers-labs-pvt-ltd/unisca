package org.panthers.labs.chimera.unisca.logging;

import com.google.common.base.Preconditions;
import org.panthers.labs.chimera.unisca.tags.annotation.Private;

import java.util.HashMap; /**
 * Wrapper class for log messages that include a logging context.
 * This is used as the return type of the string interpolator `LogStringContext`.
 */
@Private
public record MessageWithContext(String message, HashMap<String, String> context) {

    public MessageWithContext merge(MessageWithContext messageWithContext) {
        Preconditions.checkNotNull(messageWithContext);
        HashMap<String, String> resultMap = new HashMap<>(this.context);
        resultMap.putAll(messageWithContext.context);
        return new MessageWithContext(this.message + messageWithContext.message, resultMap);
    }

    public MessageWithContext stripMargin() {
        return new MessageWithContext(this.message.strip(), this.context);
    }
}
