package kz.amixady.shyft.modules.input.factory;


import kz.amixady.shyft.exeptions.FileOpenException;
import kz.amixady.shyft.modules.input.inputfile.InputFileValidator;
import kz.amixady.shyft.modules.input.reader.LineReader;
import kz.amixady.shyft.modules.input.reader.io.FileLineReader;
import kz.amixady.shyft.shared.WarningCollector;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


//фабрика, которая поочерёдно создает объекты LineReader для чтения строк из файлов
public class LineReaderFactory {
    private final List<String> inputFiles;
    private final WarningCollector warningCollector;
    private final InputFileValidator validator;
    private int currentIndex = 0;

    public LineReaderFactory(List<String> inputFiles,
                             WarningCollector warningCollector,
                             InputFileValidator validator) {
        this.inputFiles = inputFiles;
        this.warningCollector = warningCollector;
        this.validator = validator;
    }

    public Optional<LineReader> getNextReader() {
        while (currentIndex < inputFiles.size()) {
            Path path = Paths.get(inputFiles.get(currentIndex++));
            try {
                validator.validate(path);// throws validation exception
                return Optional.of(new FileLineReader(path)); // throws FileOpenException
            } catch (FileOpenException exception) {
                warningCollector.add(exception.getMessage());
            }
        }
        return Optional.empty();
    }
}
