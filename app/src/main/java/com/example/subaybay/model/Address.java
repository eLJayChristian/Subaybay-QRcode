package com.example.subaybay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private long id;
    private User user;
    private String region;
    private String province;
    private String city;
    private String barangay;
}
