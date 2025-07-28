package kz.amixady.shyft.modules.cli.factory;


import kz.amixady.shyft.modules.cli.enums.Flag;
import kz.amixady.shyft.modules.cli.enums.Option;
import kz.amixady.shyft.modules.cli.resolver.OutputConfigResolver;
import kz.amixady.shyft.shared.dto.OutputConfig;
import kz.amixady.shyft.shared.enums.WriteMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class OutputConfigFactory {

    private final OutputConfigResolver resolver;

    public OutputConfig create(Map<Option,String> options, Set<Flag> flags){
        WriteMode writeMode =
                resolver.resolveWriteMode(flags);
        String prefix =
                resolver.resolveOutputPrefix(options);
        String outputDir =
                resolver.resolveOutputDir(options);

        return new OutputConfig(writeMode,prefix,outputDir);
    }
}
