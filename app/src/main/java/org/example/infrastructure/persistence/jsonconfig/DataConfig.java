package org.example.infrastructure.persistence.jsonconfig;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataConfig {
    private static final String DEFAULT_DATA_DIR = "app/data";
    private static final String TASKS_FILE = "tasks.json";
    private static final String BOARDS_FILE = "boards.json";

    public static Path getDataDirectory() {
        String envDataDir = System.getenv("TASK_MANAGER_DATA_DIR");

        Path dataDir = envDataDir != null ?
                Paths.get(envDataDir) :
                Paths.get(System.getProperty("user.dir"), DEFAULT_DATA_DIR);

        createDirectoryIfNotExists(dataDir);

        return dataDir;
    }

    public static Path getTasksFilePath() {
        return getDataDirectory().resolve(TASKS_FILE);
    }

    public static Path getBoardsFilePath() {
        return getDataDirectory().resolve(BOARDS_FILE);
    }

    private static void createDirectoryIfNotExists(Path directory) {
        try {
            if (!Files.exists(directory)) {
                Files.createDirectory(directory);
                System.out.println("Created data directory: " + directory);
            }

        } catch (Exception e) {
            throw new RuntimeException("Could not create data directory: " + directory, e);
        }
    }
}