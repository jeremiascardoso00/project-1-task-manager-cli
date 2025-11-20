package org.example.infrastructure.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.infrastructure.persistence.jsonconfig.JsonConfig;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class JsonRepository<T> {
    protected final ObjectMapper objectMapper;
    protected final Path filePath;
    protected final Class<T> type;

    public JsonRepository(Path filePath, Class<T> type) {
        this.objectMapper = JsonConfig.createObjectMapper();
        this.filePath = filePath;
        this.type = type;
        initializeFile();
    }

    private void initializeFile() {
        try {
            if (!Files.exists(filePath)) {
                System.out.println("File does not exists");
                Files.createFile(filePath);
                saveAll(new ArrayList<>());
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not initialize data file: " + filePath, e);
        }
    }

    protected List<T> loadAll() {
        try {
            byte[] jsonData = Files.readAllBytes(filePath);
            System.out.println(filePath);

            if (jsonData.length == 0) {
                return new ArrayList<>();
            }

            return objectMapper.readValue(jsonData,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, type));

        } catch (Exception e) {
            throw new RuntimeException("Error loading data from: " + filePath, e);
        }
    }

    protected void saveAll(List<T> items) {
        try {
            String json = objectMapper.writeValueAsString(items);

            Files.write(filePath, json.getBytes());
            System.out.println(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Error saving data to: " + filePath, e);
        }
    }
}