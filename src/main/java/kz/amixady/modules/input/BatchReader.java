package kz.amixady.modules.input;

import java.util.Queue;

public interface BatchReader {
    Queue<String> readNextBatch();
}
