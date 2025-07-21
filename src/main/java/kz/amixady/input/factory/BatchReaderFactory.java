package kz.amixady.input.factory;

import kz.amixady.input.BatchReader;
import kz.amixady.input.inputfile.InputFileValidator;
import kz.amixady.input.reader.MultiFileBatchReader;
import kz.amixady.sharp.WarningCollector;

public class BatchReaderFactory {
    private final WarningCollector warningCollector;

    public BatchReaderFactory(WarningCollector warningCollector) {
        this.warningCollector = warningCollector;
    }

    public BatchReader create(String [] files){
        InputFileValidator inputFileValidator =
                new InputFileValidator();
        LineReaderFactory lineReaderFactory =
                new LineReaderFactory(files,warningCollector,inputFileValidator);
        return new MultiFileBatchReader(warningCollector,lineReaderFactory);
    }
}
