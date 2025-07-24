package kz.amixady.cli.factory;

import kz.amixady.cli.config.OutputConfig;
import kz.amixady.cli.enums.Flag;
import kz.amixady.cli.enums.Option;
import kz.amixady.cli.enums.WriteMode;

import java.util.Map;
import java.util.Set;

import static kz.amixady.sharp.DefaultArgValues.*;

public class OutputConfigFactory {
    public OutputConfig create(Map<Option,String> options, Set<Flag> flags){
        WriteMode writeMode =
                flags.contains(Flag.APPEND)?WriteMode.APPEND:DEFAULT_WRITE_MODE;
        String prefix =
                options.getOrDefault(Option.OUTPUT_PREFIX,DEFAULT_PREFIX);
        String outputDir =
                options.getOrDefault(Option.OUTPUT_DIR,DEFAULT_OUTPUT_DIR);

        return new OutputConfig(writeMode,prefix,outputDir);
    }
}
