package kz.amixady.shyft.shared.dto;


import kz.amixady.shyft.shared.enums.WriteMode;

public record OutputConfig(
        WriteMode writeMode,
        String prefix,
        String outputDir
) {

    @Override
    public String toString() {
        return "OutputConfig[" +
                "writeMode=" + writeMode +
                ", prefix='" + prefix + '\'' +
                ", outputDir='" + outputDir + '\'' +
                ']';
    }
}
