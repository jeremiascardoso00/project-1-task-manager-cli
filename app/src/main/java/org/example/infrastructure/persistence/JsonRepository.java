package org.example.infrastructure.persistence;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.infrastructure.persistence.jsonconfig.JsonConfig;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    protected Optional<List<T>> loadAll() {
        try {
            byte[] jsonData = Files.readAllBytes(filePath);

            if (jsonData.length == 0) {
                return Optional.empty();
            }

            JavaType javaType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, type);

            List<T> result = objectMapper.readValue(jsonData, javaType);

            return Optional.of(result);

        } catch (Exception e) {
            throw new RuntimeException("Error loading data from: " + filePath, e);
        }
    }

    protected void saveAll(List<T> items) {
        try {
            String json = objectMapper.writeValueAsString(items);
            Files.write(filePath, json.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Error saving data to: " + filePath, e);
        }
    }
}