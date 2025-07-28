package kz.amixady.shyft.modules.cli.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Flag {
    APPEND("-a"),
    FULL_STATS("-f"),
    SHORT_STATS("-s");

    private final String name;

    Flag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Optional<Flag> fromName(String name) {
        return Arrays.stream(values())
                .filter(flag -> flag.name.equals(name))
                .findFirst();
    }
}
