package kz.amixady.modules.input;

import java.util.List;

public interface BatchReader {
    List<String> readNextBatch();
}
