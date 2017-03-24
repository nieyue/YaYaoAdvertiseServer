package com.nieyue.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nieyue.util.Configure;


/**
 * api控制类
 * @author yy
 *
 */
@Controller("apiController")
@RequestMapping("/api")
public class APIController {
	
	/**
	 * 管理员分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/p", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String browseProperties(
			HttpSession session)  {
		String p=Configure.getOldOrigin();
			return p;
	}
	
	
}
