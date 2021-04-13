package util;

import enums.name.folder.FolderName;

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

    public static void createProfilesDirectoryIfNotPresentAtPath(File path) {
        File profiles = new File(path, FolderName.PROJECTS.folderName());
        createDirectoryIfNotPresent(profiles);
    }

    public static File getProfilesDirectoryInPath(File path) {
        return getDirectoryInPath(path, FolderName.PROJECTS.folderName());
    }
}