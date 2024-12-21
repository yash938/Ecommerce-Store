package com.Ecommerce.web.application.Dto;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RefreshTokenDto {
    private int id;
    private String token;
    private Instant expiryDate;

}
