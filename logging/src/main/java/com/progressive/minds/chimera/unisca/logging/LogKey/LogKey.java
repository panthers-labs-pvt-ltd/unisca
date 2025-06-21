package com.progressive.minds.chimera.unisca.logging.LogKey;

import java.util.Locale;

public class LogKey {
    public String getName() {
        return getClass().getSimpleName().replaceAll("\\$", "").toLowerCase(Locale.ROOT);
    }
}
