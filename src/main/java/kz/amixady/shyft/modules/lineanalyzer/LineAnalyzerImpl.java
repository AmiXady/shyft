package kz.amixady.shyft.modules.lineanalyzer;


import kz.amixady.shyft.shared.dto.StructuredLines;
import kz.amixady.shyft.shared.enums.LineType;
import kz.amixady.shyft.shared.interfaces.LineAnalyzer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LineAnalyzerImpl implements LineAnalyzer {

    private final LineTypeResolverChain lineTypeResolverChain;

    @Override
    public StructuredLines analyze(List<String> lines) {
        StructuredLines structuredLines =
                new StructuredLines();
        for(String line : lines) {
            LineType lineType =
                    lineTypeResolverChain.resolveType(line);
            structuredLines.addLine(lineType,line);
        }
        return structuredLines;
    }
}
