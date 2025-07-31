package kz.amixady.shyft.shared.interfaces;


import kz.amixady.shyft.shared.dto.StructuredLines;

import java.util.List;

public interface PartReader {
    //читает данные порциями из переданных источников (условно по 100 строк каждый раз)
    StructuredLines readParts();
}
