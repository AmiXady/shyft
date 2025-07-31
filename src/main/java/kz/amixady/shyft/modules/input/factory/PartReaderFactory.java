package kz.amixady.shyft.modules.input.factory;



import kz.amixady.shyft.modules.input.inputfile.InputFileValidator;
import kz.amixady.shyft.modules.input.reader.FilePartReader;
import kz.amixady.shyft.shared.WarningCollector;
import kz.amixady.shyft.shared.interfaces.LineAnalyzer;
import kz.amixady.shyft.shared.interfaces.PartReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PartReaderFactory {
    private final WarningCollector warningCollector;
    private final InputFileValidator inputFileValidator;
    private final LineAnalyzer lineAnalyzer;

    public PartReader create(List<String> files){
        LineReaderFactory lineReaderFactory =
                new LineReaderFactory(files,warningCollector,inputFileValidator);
        return new FilePartReader(warningCollector,lineReaderFactory,lineAnalyzer);
    }
}
