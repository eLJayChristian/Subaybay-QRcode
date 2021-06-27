package com.example.subaybay.dto;

import com.example.subaybay.model.Establishment;
import com.example.subaybay.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScannedByUserByEstablisment {
    private long id;
    private User user;
    private double temperature;
    private Establishment scanByWhatEstablihsment;
    private String dateScanned;
}
