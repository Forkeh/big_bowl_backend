package kea.exam.template.exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String entityName, Long id) {
        super(entityName + " with id " + id + " not found");
    }

    public EntityNotFoundException(String entityName, String name) {
        super(entityName + " with name " + name + " not found");
    }
}
