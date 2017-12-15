package com.bookrental.Exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public String resourceId;

    public ResourceNotFoundException(String id, String message) {
        super(message);
        this.resourceId = id;
    }
}
