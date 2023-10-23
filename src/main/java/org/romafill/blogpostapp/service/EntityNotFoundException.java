package org.romafill.blogpostapp.service;

public class EntityNotFoundException extends RuntimeException {
    private final long entityId;
    private final String entityName;
    public EntityNotFoundException(String entityName, long entityId) {
        this.entityId = entityId;
        this.entityName = entityName;
    }

    public long getEntityId() {
        return entityId;
    }

    public String getEntityName() {
        return entityName;
    }


    @Override
    public String toString() {
        return "Entity " + entityName + " with id " + entityId + " was not found";
    }
}
