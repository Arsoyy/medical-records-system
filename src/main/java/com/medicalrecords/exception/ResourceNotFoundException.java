package com.medicalrecords.exception;

/**
 * Изключение, което се хвърля при липса
 * на търсен ресурс в базата данни.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}