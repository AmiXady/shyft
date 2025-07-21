package kz.amixady.input.factory;

import kz.amixady.exception.FileOpenException;
import kz.amixady.input.inputfile.InputFileValidator;
import kz.amixady.input.reader.io.FileLineReader;
import kz.amixady.input.reader.LineReader;
import kz.amixady.sharp.WarningCollector;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;


//фабрика, которая поочерёдно создает объекты LineReader для чтения строк из файлов
public class LineReaderFactory {
    private final String [] inputFiles;
    private final WarningCollector warningCollector;
    private final InputFileValidator validator;
    private int currentIndex = 0;

    public LineReaderFactory(String[] inputFiles,
                             WarningCollector warningCollector,
                             InputFileValidator validator) {
        this.inputFiles = inputFiles;
        this.warningCollector = warningCollector;
        this.validator = validator;
    }

    public Optional<LineReader> getNextReader() {
        while (currentIndex < inputFiles.length) {
            Path path = Paths.get(inputFiles[currentIndex++]);
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
