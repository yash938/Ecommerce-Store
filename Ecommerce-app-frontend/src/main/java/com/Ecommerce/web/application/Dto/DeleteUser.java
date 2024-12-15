package com.Ecommerce.web.application.Dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class DeleteUser {
    private String message;
    private LocalDate deletedAt;
    private HttpStatus status;

    public DeleteUser() {}

    public DeleteUser(String message, LocalDate deletedAt, HttpStatus status) {
        this.message = message;
        this.deletedAt = deletedAt;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
