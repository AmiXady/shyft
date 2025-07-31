package kz.amixady.shyft.modules.output.dto;

import kz.amixady.shyft.shared.enums.WriteMode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.nio.file.Path;
@Getter
@Setter
@Builder
public class ValidatedOutputConfig {
    private WriteMode writeMode;
    private String prefix;
    private Path outputDir;

    public ValidatedOutputConfig(WriteMode writeMode, String prefix, Path outputDir) {
        this.writeMode = writeMode;
        this.prefix = prefix;
        this.outputDir = outputDir;
    }
}
