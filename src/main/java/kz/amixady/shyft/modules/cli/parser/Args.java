package kz.amixady.shyft.modules.cli.parser;


import kz.amixady.shyft.modules.cli.enums.Flag;
import kz.amixady.shyft.modules.cli.enums.Option;

import java.util.List;
import java.util.Map;
import java.util.Set;

public record Args(
        Map<Option, String> options,
        Set<Flag> flags,
        List<String>files
) {
}
