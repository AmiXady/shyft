package kz.amixady.shyft.modules.input.reader;



import kz.amixady.shyft.exeptions.FileReadException;
import kz.amixady.shyft.modules.input.factory.LineReaderFactory;
import kz.amixady.shyft.modules.input.reader.io.LineReader;
import kz.amixady.shyft.shared.WarningCollector;
import kz.amixady.shyft.shared.dto.StructuredLines;
import kz.amixady.shyft.shared.interfaces.LineAnalyzer;
import kz.amixady.shyft.shared.interfaces.PartReader;
import kz.amixady.shyft.modules.lineanalyzer.line.LineTypeResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sound.sampled.Line;
import java.io.IOException;
import java.util.*;

import static kz.amixady.shyft.shared.constants.Constants.BATCH_SIZE;


//читает данные из переданных файлов частями, то есть за раз возвращает определеное количество строк из файлов


@Component
@RequiredArgsConstructor
public class FilePartReader implements PartReader {

    private final WarningCollector warningCollector;
    private final LineReaderFactory lineReaderFactory;

    private final LineAnalyzer lineTypeResolver;
    private LineReader currentReader;


    @Override
    public StructuredLines readParts() {
        List<Line> result = new ArrayList<>(BATCH_SIZE);
        while (result.size() < BATCH_SIZE && currentReader != null) {
            tryReadLineInto(result);
        }
        return result;
    }


    private void tryReadLineInto(List<Line> result) {
        if (currentReader.isFinished()) {
            closeAndSwitch();
            return;
        }

        try {
            String line = currentReader.readLine();
            if (line != null) {
                if(!line.isBlank()) {
                    result.add(lineTypeResolver.resolve(line));
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

