package kz.amixady.shyft.shared.interfaces;


import kz.amixady.shyft.shared.dto.Line;

import java.util.List;

public interface BatchReader {

    //читает данные порциями из переданных файлов (условно по 100 строк каждый раз)
    List<Line> readNextBatch();
}
