package util;

import java.io.File;

public class FileUtil {
    private FileUtil() {
        throw new UnsupportedOperationException("Cannot instantiate a util class");
    }

    public static File getDirectoryInPath(File path, String directoryName) {
        return new File(path, directoryName);
    }

    public static void createDirectoryIfNotPresent(File directory) {
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
}