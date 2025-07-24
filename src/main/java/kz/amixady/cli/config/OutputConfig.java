package kz.amixady.cli.config;

import kz.amixady.cli.enums.WriteMode;

public record OutputConfig(
        WriteMode writeMode,
        String prefix,
        String outputDir
) {
}
