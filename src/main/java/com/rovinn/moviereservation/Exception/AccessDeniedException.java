package com.rovinn.moviereservation.Exception;

public class AccessDeniedException extends RuntimeException
{
    public AccessDeniedException(String message) {
        super(message);
    }
}
