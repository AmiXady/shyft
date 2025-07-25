package kz.amixady.modules.input.reader;

import kz.amixady.modules.input.BatchReader;
import kz.amixady.modules.input.factory.LineReaderFactory;
import kz.amixady.modules.line.Line;
import kz.amixady.modules.line.LineTypeResolver;
import kz.amixady.sharp.WarningCollector;

import java.io.IOException;
import java.util.*;

import static kz.amixady.sharp.Constants.BATCH_SIZE;
import static kz.amixady.sharp.WarninMessage.FILE_READ_ERROR;

public class MultiFileBatchReader implements BatchReader {
    private final WarningCollector warningCollector;
    private final LineReaderFactory lineReaderFactory;
    private final LineTypeResolver lineTypeResolver;
    private LineReader currentReader;

    public MultiFileBatchReader(WarningCollector warningCollector,
                                LineReaderFactory lineReaderFactory,
                                LineTypeResolver lineTypeResolver) {
        this.warningCollector = warningCollector;
        this.lineReaderFactory = lineReaderFactory;
        this.lineTypeResolver = lineTypeResolver;
        switchToNextReader();
    }

    @Override
    public List<Line> readNextBatch() {
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
        } catch (IOException e) {
            warningCollector.add(FILE_READ_ERROR);
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

