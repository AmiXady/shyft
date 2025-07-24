package kz.amixady.cli.factory;

import kz.amixady.cli.parser.ParsedArgs;
import kz.amixady.cli.config.CliConfig;
import kz.amixady.cli.config.OutputConfig;
import kz.amixady.cli.enums.Flag;
import kz.amixady.cli.enums.StatsMode;

import java.util.Set;

public class CliConfigFactory {

    private final OutputConfigFactory outputConfigFactory;

    public CliConfigFactory(OutputConfigFactory outputConfigFactory) {
        this.outputConfigFactory = outputConfigFactory;
    }

    public CliConfig create(ParsedArgs parsedArgs){
        Set<Flag> flags = parsedArgs.flags();

        OutputConfig outputConfig =
                outputConfigFactory.create(parsedArgs.options(),flags);

        StatsMode statsMode = StatsMode.NONE;
        if(flags.contains(Flag.FULL_STATS)){
            statsMode = StatsMode.FULL;
        } else if (flags.contains(Flag.SHORT_STATS)) {
            statsMode = StatsMode.SHORT;
        }

        return new CliConfig(parsedArgs.files(),outputConfig,statsMode);
    }
}
