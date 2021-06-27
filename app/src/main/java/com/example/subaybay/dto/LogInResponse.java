package com.example.subaybay.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogInResponse {
    private String authenticationToken;
    private String mobileNumber;
    private String refreshToken;
    private String expiresAt;
    
}
