package com.example.subaybay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Establishment {
    private long id;
    private String establishmentName;
    private long mobileNumber;
    private String email;
    private String password;
    private String ownerFullName;
    private boolean isVerified;
    private String dateRegistered;
}
