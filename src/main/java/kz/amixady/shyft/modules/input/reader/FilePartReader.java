package kz.amixady.shyft.modules.input.reader;



import kz.amixady.shyft.exeptions.FileReadException;
import kz.amixady.shyft.modules.input.factory.LineReaderFactory;
import kz.amixady.shyft.modules.input.reader.io.LineReader;
import kz.amixady.shyft.shared.WarningCollector;
import kz.amixady.shyft.shared.dto.StructuredLines;
import kz.amixady.shyft.shared.interfaces.LineAnalyzer;
import kz.amixady.shyft.shared.interfaces.PartReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sound.sampled.Line;
import java.io.IOException;
import java.util.*;

import static kz.amixady.shyft.shared.constants.Constants.BATCH_SIZE;


//читает данные из переданных файлов частями, то есть за раз возвращает определеное количество строк из файлов


public class FilePartReader implements PartReader {

    private final WarningCollector warningCollector;
    private final LineReaderFactory lineReaderFactory;

    private final LineAnalyzer lineTypeResolver;
    private LineReader currentReader;

    public FilePartReader(WarningCollector warningCollector,
                          LineReaderFactory lineReaderFactory,
                          LineAnalyzer lineTypeResolver) {
        this.warningCollector = warningCollector;
        this.lineReaderFactory = lineReaderFactory;
        this.lineTypeResolver = lineTypeResolver;
    }

    @Override
    public StructuredLines readParts() {
        List<String> piece = new ArrayList<>(BATCH_SIZE);
        while (piece.size() < BATCH_SIZE && currentReader != null) {
            readLineInto(piece);
        }
        return lineTypeResolver.analyze(piece);
    }

    private void readLineInto(List<String> piece ) {
        if (currentReader.isFinished()) {
            closeAndSwitch();
            return;
        }

        try {
            String line = currentReader.readLine();
            if (line != null) {
                if(!line.isBlank()) {
                    piece.add(line);
                }
            } else {
                closeAndSwitch();
            }
        } catch (FileReadException e) {
            warningCollector.add(e.getMessage());
            closeAndSwitch();
        }
    }


    private void closeAndSwitch() {
        try {
            currentReader.close();
        } catch (IOException ignored) {
            /// ?
        } finally {
            switchToNextReader();
        }
    }

    private void switchToNextReader() {
        Optional<LineReader> nextReader =
                lineReaderFactory.getNextReader();
        currentReader = nextReader.orElse(null);
    }
}

