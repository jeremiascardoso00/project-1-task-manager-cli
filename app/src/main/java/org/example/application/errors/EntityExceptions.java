package org.example.application.errors;


public class EntityExceptions extends RuntimeException {

    private final String entityId;
    private final String errorCode;

    public EntityExceptions(String message, String entityId, String errorCode){
        super(message);
        this.entityId = entityId;
        this.errorCode = errorCode;
    }

    public EntityExceptions(String message, String entityId, String errorCode, Throwable cause){
        super(message,cause);
        this.entityId = entityId;
        this.errorCode = errorCode;
    }

    // Getters
    public String getEntityId() { return entityId; }
    public String getErrorCode() { return errorCode; }

}
