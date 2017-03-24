package com.nieyue.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nieyue.util.Configure;
import com.nieyue.util.MyOriginUtil;


/**
 * 过滤请求实现rest风格
 * @author yy
 *
 */
public class JspFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		 // 获得在下面代码中要用的request,response
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        servletResponse=MyOriginUtil.getOriginPugin(servletRequest, servletResponse);//origin配置
        //chain.doFilter(request, response);
        // 获得用户请求的http://localhost:8080/YaYaoMall/mall/mobile/index
        // 获得用户请求的http://localhost:8080/mall/mobile/indexp
        String rpath = servletRequest.getRequestURI(); 						// /YaYaoMall/mall/mobile/index /mall/mobile/index 
        String path=servletRequest.getServletPath();						// /mall/mobile/index           /mall/mobile/index
        String cpath = servletRequest.getContextPath(); 					// /YaYaoMall  					空 
        String strBackUrl=servletRequest.getRealPath("/");					// D:\nieyue\tomcat\apache-tomcat-7.0.57-windows-x64\apache-tomcat-7.0.57\webapps\YaYaoMall\ 
        String strServletUrl=servletRequest.getServletContext().getRealPath("");// D:\nieyue\tomcat\apache-tomcat-7.0.57-windows-x64\apache-tomcat-7.0.57\webapps\YaYaoMall 
     
        if(rpath.startsWith("/resources")&&new File(strServletUrl+rpath).exists()){
			servletRequest.getRequestDispatcher(rpath).forward(request, response);
		}else if(new File(strServletUrl+rpath+".html").exists()){
				servletRequest.getRequestDispatcher(rpath+".html").forward(request,response);
		}else{
				chain.doFilter(request, response);
			}
	//}
		
}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	

}
