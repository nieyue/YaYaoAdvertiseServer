package com.nieyue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nieyue.bean.Website;
import com.nieyue.exception.StateResult;
import com.nieyue.service.WebsiteService;


/**
 * 网站控制类
 * @author yy
 *
 */
@Controller("websiteController")
@RequestMapping("/website")
public class WebsiteController {
	@Resource
	private WebsiteService websiteService;
	
	/**
	 * 网站分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Website> browsePagingWebsite(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="website_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Website> list = new ArrayList<Website>();
			list= websiteService.browsePagingWebsite(pageNum, pageSize, orderName, orderWay);
			return list;
	}
	/**
	 * 根据adminId网站分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list/admin", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Website> browsePagingWebsiteByAdminId(
			@RequestParam(value="adminId")Integer adminId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="website_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
		List<Website> list = new ArrayList<Website>();
		list= websiteService.browsePagingWebsiteByAdminId(adminId,pageNum, pageSize, orderName, orderWay);
		return list;
	}
	/**
	 * 网站修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateWebsite(@ModelAttribute Website website,HttpSession session)  {
		boolean um = websiteService.updateWebsite(website);
		return StateResult.getSR(um);
	}
	/**
	 * 网站增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addWebsite(@ModelAttribute Website website, HttpSession session) {
		boolean am = websiteService.addWebsite(website);
		return StateResult.getSR(am);
	}
	/**
	 * 网站删除
	 * @return
	 */
	@RequestMapping(value = "/{websiteId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delWebsite(@PathVariable("websiteId") Integer websiteId,HttpSession session)  {
		boolean dm = websiteService.delWebsite(websiteId);
		return StateResult.getSR(dm);
	}
	/**
	 * 网站浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = websiteService.countAll();
		return count;
	}
	/**
	 * 根据adminId网站浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count/{adminId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAllByAdminId(@PathVariable("adminId")Integer adminId,HttpSession session)  {
		int count = websiteService.countAllByAdminId(adminId);
		return count;
	}
	/**
	 * 网站单个加载
	 * @return
	 */
	@RequestMapping(value = "/{websiteId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Website loadWebsite(@PathVariable("websiteId") Integer websiteId,HttpSession session)  {
		Website website=new Website();
		website = websiteService.loadWebsite(websiteId);
		return website;
	}
	
}
