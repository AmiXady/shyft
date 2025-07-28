package kz.amixady.shyft.modules.cli.enums;


import java.util.Arrays;
import java.util.Optional;

public enum Option {
    OUTPUT_DIR("-o"),
    OUTPUT_PREFIX("-p");

    private final String name;

    Option(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Optional<Option> fromName(String name) {
        return Arrays.stream(values())
                .filter(opt -> opt.name.equals(name))
                .findFirst();
    }
}