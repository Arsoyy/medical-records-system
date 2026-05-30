package com.medicalrecords.exception;

/**
 * Изключение при опит за създаване
 * на вече съществуващ запис.
 */
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}