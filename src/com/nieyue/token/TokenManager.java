package com.nieyue.token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对token进行操作的接口
 * @author ScienJus
 * @date 2015/7/31.
 */
public interface TokenManager {

    /**
     * 创建一个token关联上指定用户
     * @param userId 指定用户的id
     * @return 生成的token
     */
    public TokenModel createToken(String projectName,Integer adminId,HttpServletRequest request,HttpServletResponse response);

    /**
     * 检查token是否有效
     * @param model token
     * @return 是否有效
     */
    public boolean checkToken(String projectName,TokenModel model,HttpServletRequest request,HttpServletResponse response);

    /**
     * 从字符串中解析token
     * @param authentication 加密后的字符串
     * @return
     */
    public TokenModel getToken(String projectName,HttpServletRequest request);

    /**
     * 清除token
     * @param userId 登录用户的id
     */
    public void deleteToken(String projectName, HttpServletRequest request,HttpServletResponse response);

}
