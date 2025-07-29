package kz.amixady.shyft.modules.input.factory;



import kz.amixady.shyft.modules.input.inputfile.InputFileValidator;
import kz.amixady.shyft.modules.input.reader.MultiFileBatchReader;
import kz.amixady.shyft.modules.line.LineTypeResolverImpl;
import kz.amixady.shyft.shared.WarningCollector;
import kz.amixady.shyft.shared.interfaces.BatchReader;
import kz.amixady.shyft.shared.interfaces.LineTypeResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BatchReaderFactory {
    private final WarningCollector warningCollector;
    private final InputFileValidator inputFileValidator;

    public BatchReader create(List<String> files){
        LineReaderFactory lineReaderFactory =
                new LineReaderFactory(files,warningCollector,inputFileValidator);
        LineTypeResolver lineTypeResolver =
                new LineTypeResolverImpl();
        return new MultiFileBatchReader(warningCollector,lineReaderFactory,lineTypeResolver);
    }
}
