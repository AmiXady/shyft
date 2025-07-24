package kz.amixady.modules.input.factory;

import kz.amixady.exception.FileOpenException;
import kz.amixady.modules.input.inputfile.InputFileValidator;
import kz.amixady.modules.input.reader.io.FileLineReader;
import kz.amixady.modules.input.reader.LineReader;
import kz.amixady.sharp.WarningCollector;

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
                validator.validate(path);// throws FileOpenException;
                return Optional.of(new FileLineReader(path)); //toje
            } catch (FileOpenException e) {
                warningCollector.add(e.getMessage());
            }
        }
        return Optional.empty();
    }
}
