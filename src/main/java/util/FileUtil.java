package util;

import enums.name.action.ActionName;
import enums.name.folder.FolderName;
import model.common.Actions;

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

    public static File createProfilesDirectoryIfNotPresentAtPath(File path) {
        return createDirectoryIfNotPresentAtPath(path, FolderName.PROFILES.folderName());
    }

    public static File getProfilesDirectoryInPath(File path) {
        return getDirectoryInPath(path, FolderName.PROFILES.folderName());
    }

    public static File createDirectoryIfNotPresentAtPath(File path, String directoryName) {
        File directory = FileUtil.getDirectoryInPath(path, directoryName);
        createDirectoryIfNotPresent(directory);
        return directory;
    }

    public static void buildDirectoriesAtPathForActions(File path, Actions actions) {
        if (actions.getAction0_0() != null) {
            createDirectoryIfNotPresentAtPath(path, ActionName.ACTION_0_0.getName());
        }

        if (actions.getAction0_1() != null) {
            createDirectoryIfNotPresentAtPath(path, ActionName.ACTION_0_1.getName());
        }

        if (actions.getAction0_2() != null) {
            createDirectoryIfNotPresentAtPath(path, ActionName.ACTION_0_2.getName());
        }

        if (actions.getAction1_0() != null) {
            createDirectoryIfNotPresentAtPath(path, ActionName.ACTION_1_0.getName());
        }

        if (actions.getAction1_1() != null) {
            createDirectoryIfNotPresentAtPath(path, ActionName.ACTION_1_1.getName());
        }

        if (actions.getAction1_2() != null) {
            createDirectoryIfNotPresentAtPath(path, ActionName.ACTION_1_2.getName());
        }

        if (actions.getAction2_0() != null) {
            createDirectoryIfNotPresentAtPath(path, ActionName.ACTION_2_0.getName());
        }

        if (actions.getAction2_1() != null) {
            createDirectoryIfNotPresentAtPath(path, ActionName.ACTION_2_1.getName());
        }

        if (actions.getAction2_2() != null) {
            createDirectoryIfNotPresentAtPath(path, ActionName.ACTION_2_2.getName());
        }

        if (actions.getAction3_0() != null) {
            createDirectoryIfNotPresentAtPath(path, ActionName.ACTION_3_0.getName());
        }

        if (actions.getAction3_1() != null) {
            createDirectoryIfNotPresentAtPath(path, ActionName.ACTION_3_1.getName());
        }

        if (actions.getAction3_2() != null) {
            createDirectoryIfNotPresentAtPath(path, ActionName.ACTION_3_2.getName());
        }

        if (actions.getAction4_0() != null) {
            createDirectoryIfNotPresentAtPath(path, ActionName.ACTION_4_0.getName());
        }

        if (actions.getAction4_1() != null) {
            createDirectoryIfNotPresentAtPath(path, ActionName.ACTION_4_1.getName());
        }

        if (actions.getAction4_2() != null) {
            createDirectoryIfNotPresentAtPath(path, ActionName.ACTION_4_2.getName());
        }
    }
}