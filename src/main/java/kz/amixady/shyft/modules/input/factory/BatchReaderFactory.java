package kz.amixady.shyft.modules.input.factory;



import kz.amixady.shyft.modules.input.inputfile.InputFileValidator;
import kz.amixady.shyft.modules.input.reader.FilePartReader;
import kz.amixady.shyft.modules.lineanalyzer.line.LineTypeResolverImpl;
import kz.amixady.shyft.shared.WarningCollector;
import kz.amixady.shyft.shared.interfaces.PartReader;
import kz.amixady.shyft.modules.lineanalyzer.line.LineTypeResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BatchReaderFactory {
    private final WarningCollector warningCollector;
    private final InputFileValidator inputFileValidator;

    public PartReader create(List<String> files){
        LineReaderFactory lineReaderFactory =
                new LineReaderFactory(files,warningCollector,inputFileValidator);
        LineTypeResolver lineTypeResolver =
                new LineTypeResolverImpl();
        return new FilePartReader(warningCollector,lineReaderFactory,lineTypeResolver);
    }
}
