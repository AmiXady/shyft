package kz.amixady.shyft.modules.cli.resolver;

import kz.amixady.shyft.modules.cli.enums.Flag;
import kz.amixady.shyft.modules.cli.enums.Option;
import kz.amixady.shyft.shared.constants.DefaultArgValues;
import kz.amixady.shyft.shared.enums.WriteMode;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class OutputConfigResolver {
    public  WriteMode resolveWriteMode(Set<Flag> flags) {
        return flags.contains(Flag.APPEND) ? WriteMode.APPEND : DefaultArgValues.DEFAULT_WRITE_MODE;
    }

    public  String resolveOutputPrefix(Map<Option, String> options) {
        return options.getOrDefault(Option.OUTPUT_PREFIX, DefaultArgValues.DEFAULT_PREFIX);
    }

    public  String resolveOutputDir(Map<Option, String> options) {
        return options.getOrDefault(Option.OUTPUT_DIR, DefaultArgValues.DEFAULT_OUTPUT_DIR);
    }
}
