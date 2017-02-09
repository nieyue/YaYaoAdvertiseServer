package com.nieyue.token;
/**
 * Token的Model类，可以增加字段提高安全性，例如时间戳、url签名
 * @author ScienJus
 * @date 2015/7/31.
 */
public class TokenModel {
	 //用户id
    private Integer adminId;

    //随机生成的uuid
    private String token;

    public TokenModel(Integer adminId, String token) {
        this.adminId = adminId;
        this.token = token;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setadminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
