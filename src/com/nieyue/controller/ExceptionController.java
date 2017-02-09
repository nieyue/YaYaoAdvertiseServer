package com.nieyue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nieyue.exception.StateResult;


/**
 * 统一异常处理
 * @author yy
 *
 */
@Controller("exceptionController")
@RequestMapping("/exception")
public class ExceptionController {
	/**
	 * 普通异常
	 * @return
	 */
	@RequestMapping(value="/fail",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult test2(){
		return StateResult.getFail();
		
	}
	/**
	 * SellerSession
	 * @return
	 */
	@RequestMapping(value="/sellerSession",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult SellerSession(){
		return StateResult.getSlefSR(40001, "没有权限");
		
	}
	/**
	 * a
	 * @return
	 */
	@RequestMapping(value="/a",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String SellerSessiona(){
		return "redirect:/exception/sellerSession";
		
	}
}
