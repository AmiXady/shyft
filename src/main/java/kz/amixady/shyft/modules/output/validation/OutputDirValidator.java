package kz.amixady.shyft.modules.output.validation;


import kz.amixady.shyft.modules.output.dto.ValidatedOutputConfig;
import kz.amixady.shyft.shared.WarningCollector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutputDirValidator {

    private final WarningCollector warningCollector;


    public void validate(ValidatedOutputConfig config){



    }
}
