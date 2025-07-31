package kz.amixady.shyft.modules.cli.resolver;

import kz.amixady.shyft.modules.cli.parser.Args;
import kz.amixady.shyft.modules.cli.factory.OutputConfigFactory;
import kz.amixady.shyft.modules.cli.parser.Parser;
import kz.amixady.shyft.shared.dto.CliConfig;
import kz.amixady.shyft.shared.dto.OutputConfig;
import kz.amixady.shyft.shared.enums.StatsMode;
import kz.amixady.shyft.shared.interfaces.CliConfigResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CliConfigResolverImpl implements CliConfigResolver {
    private final StatsModeResolver statsModeResolver;
    private final OutputConfigFactory outputConfigFactory;
    private final Parser parser;


    @Override
    public CliConfig resolve(String[] args) {
        Args parsedArgs =
                parser.parse(args);
        var flags =
                parsedArgs.flags();
        var options =
                parsedArgs.options();

        OutputConfig outputConfig =
                outputConfigFactory.create(options,flags);

        StatsMode statsMode =
                statsModeResolver.resolve(flags);

        return new CliConfig(parsedArgs.files(),outputConfig,statsMode);
    }
}
