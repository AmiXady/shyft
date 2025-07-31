package kz.amixady.shyft.modules.output.validation;


import kz.amixady.shyft.modules.output.dto.ValidatedOutputConfig;
import kz.amixady.shyft.shared.dto.OutputConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OutputValidator {
    private final OutputDirValidator outputDirValidator;
    private final OutputPrefixValidator outputPrefixValidator;

    public ValidatedOutputConfig validate(OutputConfig outputConfig){

    }
}
