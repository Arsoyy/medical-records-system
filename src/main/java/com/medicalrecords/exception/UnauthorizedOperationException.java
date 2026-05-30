package com.medicalrecords.exception;

/**
 * Изключение при опит за извършване
 * на операция без необходимите права.
 */
public class UnauthorizedOperationException extends RuntimeException {

    public UnauthorizedOperationException(String message) {
        super(message);
    }
}