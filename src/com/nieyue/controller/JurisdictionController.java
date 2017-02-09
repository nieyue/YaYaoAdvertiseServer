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

import com.nieyue.bean.Jurisdiction;
import com.nieyue.exception.StateResult;
import com.nieyue.service.JurisdictionService;


/**
 * 权限控制类
 * @author yy
 *
 */
@Controller("jurisdictionController")
@RequestMapping("/jurisdiction")
public class JurisdictionController {
	@Resource
	private JurisdictionService jurisdictionService;
	
	/**
	 * 权限分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Jurisdiction> browsePagingJurisdiction(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="jurisdiction_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Jurisdiction> list = new ArrayList<Jurisdiction>();
			list= jurisdictionService.browsePagingJurisdiction(pageNum, pageSize, orderName, orderWay);
			return list;
	}
	/**
	 * 权限修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateJurisdiction(@ModelAttribute Jurisdiction jurisdiction,HttpSession session)  {
		boolean um = jurisdictionService.updateJurisdiction(jurisdiction);
		return StateResult.getSR(um);
	}
	/**
	 * 权限增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addJurisdiction(@ModelAttribute Jurisdiction jurisdiction, HttpSession session) {
		boolean am = jurisdictionService.addJurisdiction(jurisdiction);
		return StateResult.getSR(am);
	}
	/**
	 * 权限删除
	 * @return
	 */
	@RequestMapping(value = "/{jurisdictionId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delJurisdiction(@PathVariable("jurisdictionId") Integer jurisdictionId,HttpSession session)  {
		boolean dm = jurisdictionService.delJurisdiction(jurisdictionId);
		return StateResult.getSR(dm);
	}
	/**
	 * 权限浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = jurisdictionService.countAll();
		return count;
	}
	/**
	 * 权限单个加载
	 * @return
	 */
	@RequestMapping(value = "/{jurisdictionId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Jurisdiction loadJurisdiction(@PathVariable("jurisdictionId") Integer jurisdictionId,HttpSession session)  {
		Jurisdiction jurisdiction=new Jurisdiction();
		jurisdiction = jurisdictionService.loadJurisdiction(jurisdictionId);
		return jurisdiction;
	}
	
}
