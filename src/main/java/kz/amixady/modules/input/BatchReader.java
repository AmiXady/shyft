package kz.amixady.modules.input;

import kz.amixady.modules.line.Line;

import java.util.List;

public interface BatchReader {
    List<Line> readNextBatch();
}
