package org.example.application.usecases.models.responses;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class BaseGetResult<T> {
    private final boolean success;
    private final String message;
    private final List<T> items;
    private final boolean hasItems;

    protected BaseGetResult(boolean success, String message, List<T> items) {
        this.success = success;
        this.message = message;
        this.items = items != null ? items : Collections.emptyList();
        this.hasItems = !this.items.isEmpty();
    }

    // Getters
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public List<T> getItems() { return items; }
    public boolean hasItems() { return hasItems; }

    // Optional-based methods
    public Optional<List<T>> getItemsOptional() {
        return hasItems ? Optional.of(items) : Optional.empty();
    }

    public Optional<T> getFirstItem() {
        return hasItems ? Optional.of(items.get(0)) : Optional.empty();
    }

    // Utility methods
    public boolean isEmpty() {
        return !hasItems;
    }

    public int getCount() {
        return items.size();
    }
}