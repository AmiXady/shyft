package kz.amixady.cli.parser;

import kz.amixady.cli.enums.Flag;
import kz.amixady.cli.enums.Option;

import java.util.List;
import java.util.Map;
import java.util.Set;

public record ParsedArgs(
        Map<Option, String> options,
        Set<Flag> flags,
        List<String>files
) {
}
