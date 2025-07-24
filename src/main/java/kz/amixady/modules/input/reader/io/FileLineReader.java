package kz.amixady.modules.input.reader.io;
import kz.amixady.exception.FileOpenException;
import kz.amixady.modules.input.reader.LineReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static kz.amixady.sharp.WarninMessage.FILE_OPEN_ERROR;

public class FileLineReader implements LineReader {
    private final BufferedReader bufferedReader;
    private boolean finished;

    public FileLineReader(Path path) throws FileOpenException {
        try {
            this.bufferedReader = Files.newBufferedReader(path);
            this.finished = false;
        } catch (IOException e) {
            throw new FileOpenException(String.format(FILE_OPEN_ERROR,path.toString()));
        }
    }

    @Override
    public String readLine() throws IOException {
        if (finished) {
            return null;
        }
        String line = bufferedReader.readLine();
        if (line == null) {
            finished = true;
        }
        return line;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }


    public void close() throws IOException {
        bufferedReader.close();
    }
}
