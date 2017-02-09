package com.nieyue.token;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
/**
 * 通过Redis存储和验证token的实现类
 */
@Service("redisTokenManagerImpl")
public class RedisTokenManagerImpl implements TokenManager {
	@Resource
	StringRedisTemplate stringRedisTemplate;

	@Override
	public TokenModel createToken(String projectName,Integer adminId ,HttpServletRequest request,HttpServletResponse response) {
		//判断是否已经存在 ,存在先删除，不允许两个一样的用户
		deleteToken(projectName, request, response);
		
		 String token = UUID.randomUUID().toString().replace("-", "");
		 TokenModel model = new TokenModel(adminId, token);
		 //存储在redis
		 stringRedisTemplate.boundValueOps(projectName+adminId.toString()).set(token, 1800, TimeUnit.SECONDS);
		 //存储cookie客户端
		Cookie cookie = new Cookie(projectName+adminId.toString(), token);  
        cookie.setMaxAge(1800);// 设置为30min  
        cookie.setPath("/");  
        response.addCookie(cookie); 
		return model;
	}

	@Override
	public boolean checkToken(String projectName,TokenModel model,HttpServletRequest request,HttpServletResponse response) {
			if (model == null) {
	            return false;
	        }
	        String token = stringRedisTemplate.boundValueOps(projectName+model.getAdminId().toString()).get();
	        if (token == null || !token.equals(model.getToken())) {
	            return false;
	        }
	        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
	        stringRedisTemplate.boundValueOps(projectName+model.getAdminId().toString()).expire(1800, TimeUnit.SECONDS);
	        Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组  
	         if (null!=cookies) {  
	             for(Cookie cookie : cookies){  
	            	 if(cookie.getName().indexOf(projectName)>-1){
                        cookie.setPath("/"); 
	            		 cookie.setMaxAge(1800);// 设置为30min  
                        response.addCookie(cookie);  
	            		 
	            	 }
	             }  
	         }  
	        return true;
	}

	@Override
	public TokenModel getToken(String projectName,HttpServletRequest request) {
		 Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组  
		 Integer id = null;
		 String ck = null;
         if (null!=cookies) {  
             for(Cookie cookie : cookies){  
            	 if(cookie.getName().indexOf(projectName)>-1){
            		 id = Integer.valueOf(cookie.getName().substring(projectName.length()));
            		 ck=cookie.getValue();
            		 
            	 }
             }  
         }  
         if(id==null || ck==null){
        	 return null;
         }
         return new TokenModel(id, ck);
		
	}

	@Override
	public void deleteToken(String projectName,HttpServletRequest request,HttpServletResponse response) {
		 Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组  
         if (null!=cookies) {  
             for(Cookie cookie : cookies){  
            	 if(cookie.getName().indexOf(projectName)>-1){
            		 cookie.setValue(null);  
                     cookie.setMaxAge(0);// 立即销毁cookie  
                     cookie.setPath("/"); 
                     stringRedisTemplate.delete(cookie.getName());
                     response.addCookie(cookie);  
            		 
            	 }
             }  
         }  
	}
}
