package com.example.subaybay.constants;

public class ConstantVariables {
    public static final String BASE_URL = "https://subaybay.herokuapp.com/";

    // signup endpoint
    public static final String SIGNUP_URL = "subaybay/auth/signup";

    //verify endpoint
    public static final String VERIFY_URL = "subaybay/auth/verify/{otp}";

    // log in endpoint
    public static final String LOGIN_URL = "subaybay/auth/login";

    // get user profile endpoint
    public static final String GETUSERPROFILE_URL = "/subaybay/user/profile/{mobileNumber}";

    // logout endpoint
    public static final String LOGOUT_URL = "subaybay/auth/logout";

    // get all user's address endpoint
    public static final String GETALLUSERADDRESS_URL = "/subaybay/user/profile/getalluseradress/{userid}";

    // history endpoint
    public static final String GETUSERHISTORY_URL = "/subaybay/user/profile/history/{mobileNumber}";

    // refresh token endpoint
    public static final String REFRESHTOKEN_URL = "/refresh/token";

    // update user info endpoint
    public static final String UPDATEUSER_URL = "/subaybay/user/profile/update/{mobileNumber}";
}
