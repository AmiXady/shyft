package kz.amixady.shyft.modules.cli.factory;



import kz.amixady.shyft.modules.cli.enums.Flag;
import kz.amixady.shyft.modules.cli.Args;
import kz.amixady.shyft.modules.cli.parser.Parser;
import kz.amixady.shyft.modules.cli.resolver.StatsModeResolver;
import kz.amixady.shyft.shared.constants.DefaultArgValues;
import kz.amixady.shyft.shared.dto.CliConfig;
import kz.amixady.shyft.shared.dto.OutputConfig;
import kz.amixady.shyft.shared.enums.StatsMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
@RequiredArgsConstructor
public class CliConfigFactory {
    private final Parser parse;
    private final OutputConfigFactory outputConfigFactory;
    private final StatsModeResolver statsModeResolver;

    public CliConfig create(String [] args){
        Args parsedArgs =
                parse.parse(args);

        Set<Flag> flags = parsedArgs.flags();

        OutputConfig outputConfig =
                outputConfigFactory.create(parsedArgs.options(),flags);

        StatsMode statsMode =
                statsModeResolver.resolve(flags);

        return new CliConfig(parsedArgs.files(),outputConfig,statsMode);
    }
}
