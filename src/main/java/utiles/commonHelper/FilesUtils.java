package utiles.commonHelper;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FilesUtils {
    private FilesUtils() {
        super();
    }

    public static File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            LogsUtils.warn("No files found in directory: " + folderPath);
            return null;
        }
        File latestFile = files[0];
        for (File file : files) {
            if (file.lastModified() > latestFile.lastModified()) {
                latestFile = file;
            }
        }
        return latestFile;
    }

    public static void deleteFiles(File dirPath) {
        if (dirPath == null || !dirPath.exists()) {
            LogsUtils.warn("Directory does not exist" + dirPath);
            return;
        }
        File[] filesList = dirPath.listFiles();
        if (filesList == null) {
            LogsUtils.warn("Failed to list file in:" + dirPath);
            return;
        }
        for (File file : filesList) {
            if (file.isDirectory()) {
                deleteFiles(file);
            } else {
                try {
                    Files.delete(file.toPath());
                } catch (IOException e) {
                    LogsUtils.error("Failed to delete file : " + file);
                }
            }
        }

    }
    public static void cleanDirectory(File file) {
        try {
            FileUtils.deleteQuietly(file);
        } catch (Exception exception) {
            LogsUtils.error(exception.getMessage());

        }
    }

    public static void createDirectory(File path) {
        if (!path.exists()) {
            try {
                Files.createDirectories(path.toPath());
                LogsUtils.info("Directory created: " + path);
            } catch (IOException e) {
                LogsUtils.error("Failed to create directory: " + e.getMessage());
            }
        } else {
            LogsUtils.info("Directory already exists: " + path);
        }
    }
}
