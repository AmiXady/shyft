package kz.amixady.shyft.shared.interfaces;

import kz.amixady.shyft.shared.dto.StructuredLines;

import java.util.List;

public interface LineAnalyzer {
    //принимает список срок, (который получается из инпут модуль) возвращает структированную лайны крч
    StructuredLines analyze(List<String> lines);

}
