package com.Ecommerce.web.application.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteUser {

    private String message;
    private LocalDate deletedAt;
    private HttpStatus status;
}
