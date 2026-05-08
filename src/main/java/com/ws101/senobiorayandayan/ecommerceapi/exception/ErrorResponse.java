package com.ws101.senobiorayandayan.ecommerceapi.exception;

import java.time.LocalDateTime;

/**
 * Standard error response format for API errors.
 * Provides consistent error structure for all error responses.
 * 
 * @author Ray Andayan Senobio
 * @version 1.0
 */
public class ErrorResponse {
    
    private LocalDateTime timestamp;
    private int status;
    private String errorCode;
    private String message;
    private String path;
    
    /**
     * Constructs an ErrorResponse with all fields.
     * 
     * @param timestamp 
     * @param status 
     * @param errorCode 
     * @param message 
     * @param path 
     */
    public ErrorResponse(LocalDateTime timestamp, int status, String errorCode, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
        this.path = path;
    }
    
    // Getters
    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getErrorCode() { return errorCode; }
    public String getMessage() { return message; }
    public String getPath() { return path; }
    
    // Setters
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public void setStatus(int status) { this.status = status; }
    public void setErrorCode(String errorCode) { this.errorCode = errorCode; }
    public void setMessage(String message) { this.message = message; }
    public void setPath(String path) { this.path = path; }
}