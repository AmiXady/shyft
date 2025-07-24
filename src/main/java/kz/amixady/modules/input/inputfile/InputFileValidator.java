package kz.amixady.modules.input.inputfile;


import kz.amixady.exception.FileOpenException;

import java.nio.file.Files;
import java.nio.file.Path;

import static kz.amixady.sharp.ValidationMessage.*;

public class InputFileValidator {

    public void validate(Path path) throws FileOpenException {
        String name = path.getFileName().toString();

        if (!Files.exists(path)) {
            fail(String.format(FILE_NOT_FOUND, name), name);
        }

        if (!Files.isRegularFile(path)) {
            fail(String.format(NOT_REGULAR_FILE, name), name);
        }

        if (!Files.isReadable(path)) {
            fail(String.format(FILE_NOT_READABLE, name), name);
        }

        if (!InputFileExtension.isSupported(name)) {
            fail(String.format(UNSUPPORTED_EXTENSION, name), name);
        }
    }

    private void fail(String reason, String name) throws FileOpenException {
        String validationErrorMessage =
                reason + String.format(IGNORED_SUFFIX, name);
        throw new FileOpenException(validationErrorMessage);
    }
}
