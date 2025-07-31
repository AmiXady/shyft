package kz.amixady.shyft.modules.input.reader.io;


import kz.amixady.shyft.exeptions.FileOpenException;
import kz.amixady.shyft.exeptions.FileReadException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static kz.amixady.shyft.shared.constants.WarningMessage.*;


public class FileLineReader implements LineReader {
    private final BufferedReader bufferedReader;
    private final Path path;
    private boolean finished;

    public FileLineReader(Path path) throws FileOpenException {
        try {
            this.bufferedReader = Files.newBufferedReader(path);
            this.path = path;
            this.finished = false;
        } catch (IOException e) {
            throw new FileOpenException(String.format(FILE_READ_OPEN_ERROR,path.toString()));
        }
    }

    @Override
    public String readLine() throws FileReadException {
        if (finished) {
            return null;
        }
        try {
            String line = bufferedReader.readLine();
            if (line == null) {
                finished = true;
            }
            return line;
        }
        catch (IOException ioException) {
            throw new FileReadException(String.format(FILE_READ_ERROR,path.toString()));
        }

    }

    @Override
    public boolean isFinished() {
        return finished;
    }


    public void close() throws IOException {
        bufferedReader.close();
    }
}
