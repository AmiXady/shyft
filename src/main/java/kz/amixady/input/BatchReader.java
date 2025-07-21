package kz.amixady.input;

import java.util.List;

public interface BatchReader {
    List<String> readNextBatch();
}
