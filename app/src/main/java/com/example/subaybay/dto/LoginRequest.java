package com.example.subaybay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String mobileNumber;
    // talagang mali spell ko kasi d ko na maiba sa backend tinatamad na kong ibahin ok?
    private String passsword;

}
