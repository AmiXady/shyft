package kz.amixady.shyft.modules.input.inputfile;

import kz.amixady.shyft.shared.WarningCollector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.nio.file.Files;
import java.nio.file.Path;

import static kz.amixady.shyft.shared.constants.ValidationWarningMessage.*;


@Component
@RequiredArgsConstructor
public class InputFileValidator {
    private final WarningCollector warningCollector;

    public boolean validate(Path path)  {
        String name = path.getFileName().toString();

        if (!Files.exists(path)) {
            warningCollector.add(String.format(FILE_NOT_FOUND, name));
            return false;
        }

        if (!Files.isRegularFile(path)) {
            warningCollector.add(String.format(NOT_REGULAR_FILE, name));
            return false;
        }

        if (!Files.isReadable(path)) {
            warningCollector.add(String.format(FILE_NOT_READABLE, name));
            return false;
        }

        if (!InputFileExtension.isSupported(name)) {
            warningCollector.add(String.format(UNSUPPORTED_EXTENSION, name));
            return false;
        }
        return true;
    }
}
