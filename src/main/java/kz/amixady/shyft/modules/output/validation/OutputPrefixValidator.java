package kz.amixady.shyft.modules.output.validation;
import kz.amixady.shyft.modules.output.dto.ValidatedOutputConfig;
import kz.amixady.shyft.shared.WarningCollector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static kz.amixady.shyft.shared.constants.Constants.*;
import static kz.amixady.shyft.shared.constants.DefaultArgValues.*;
import static kz.amixady.shyft.shared.constants.ValidationWarningMessage.*;


@Component
@RequiredArgsConstructor
public class OutputPrefixValidator {

    private final WarningCollector warningCollector;

    public void validate(ValidatedOutputConfig config) {
        String prefix = config.getPrefix();

        if(prefix.isBlank()) {
            config.setPrefix(DEFAULT_PREFIX);
            return;
        }

        if(!prefix.matches(PREFIX_ALLOWED_CHARS_REGEX)) {
            config.setPrefix(DEFAULT_PREFIX); // префикс по умолчанию будем использовать
            warningCollector.add(PREFIX_ERROR_ILLEGAL_CHARACTERS);
        }

        if(prefix.length()>MAX_PREFIX_LENGTH) {
            config.setPrefix(prefix.substring(MAX_PREFIX_LENGTH));//сократим префикс
            warningCollector.add(String.format(TOO_LONG_PREFIX,MAX_PREFIX_LENGTH));
        }
    }
}
