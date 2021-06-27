package com.example.subaybay.utils;

import com.example.subaybay.constants.ConstantVariables;
import com.example.subaybay.dto.RegisterRequest;

public class SubaybayAPIs {

    public static SubaybayAuthService subaybayAuthService(){
        return Client.getClient(ConstantVariables.BASE_URL).create(SubaybayAuthService.class);
    }

    public static SubaybayAuthService subaybayAuthServicePlainString(){
        return Client.getClientButPlainString(ConstantVariables.BASE_URL).create(SubaybayAuthService.class);
    }

}
