package com.qingyang.bistro.net.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.qingyang.bistro.util.MD5Util;

/**
 * Created by QingYang on 15/6/17.
 */
public class SignInRequest {


    @SerializedName("value")
    @Expose
    private String username;

    @SerializedName("sign")
    @Expose
    private String userpwd;

    public SignInRequest(String username, String passowrd){
        this.username = "J9sNwbvkH1+H0QY2e+tPN2VfIR0qDRwvw2XvX3yIeYOpc21n90Zo3A==";
        //this.userpwd = MD5Util.getMD5String(passowrd);
        this.userpwd = "f34d5613947e52776b6717752fe8a3f1";

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

}
