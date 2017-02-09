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

import com.nieyue.bean.AdvertiseRelate;
import com.nieyue.exception.StateResult;
import com.nieyue.service.AdvertiseRelateService;


/**
 * 广告位广告关系控制类
 * @author yy
 *
 */
@Controller("advertiseRelateController")
@RequestMapping("/advertiseRelate")
public class AdvertiseRelateController {
	@Resource
	private AdvertiseRelateService advertiseRelateService;
	
	/**
	 * 广告位广告关系分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<AdvertiseRelate> browsePagingAdvertiseRelate(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="advertise_relate_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<AdvertiseRelate> list = new ArrayList<AdvertiseRelate>();
			list= advertiseRelateService.browsePagingAdvertiseRelate(pageNum, pageSize, orderName, orderWay);
			return list;
	}
	/**
	 * 广告位广告关系修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateAdvertiseRelate(@ModelAttribute AdvertiseRelate advertiseRelate,HttpSession session)  {
		boolean um = advertiseRelateService.updateAdvertiseRelate(advertiseRelate);
		return StateResult.getSR(um);
	}
	/**
	 * 广告位广告关系增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addAdvertiseRelate(@ModelAttribute AdvertiseRelate advertiseRelate, HttpSession session) {
		boolean am = advertiseRelateService.addAdvertiseRelate(advertiseRelate);
		return StateResult.getSR(am);
	}
	/**
	 * 广告位广告关系删除
	 * @return
	 */
	@RequestMapping(value = "/{advertiseRelateId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delAdvertiseRelate(@PathVariable("advertiseRelateId") Integer advertiseRelateId,HttpSession session)  {
		boolean dm = advertiseRelateService.delAdvertiseRelate(advertiseRelateId);
		return StateResult.getSR(dm);
	}
	/**
	 * 广告位广告关系浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = advertiseRelateService.countAll();
		return count;
	}
	/**
	 * 广告位广告关系单个加载
	 * @return
	 */
	@RequestMapping(value = "/{advertiseRelateId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody AdvertiseRelate loadAdvertiseRelate(@PathVariable("advertiseRelateId") Integer advertiseRelateId,HttpSession session)  {
		AdvertiseRelate advertiseRelate=new AdvertiseRelate();
		advertiseRelate = advertiseRelateService.loadAdvertiseRelate(advertiseRelateId);
		return advertiseRelate;
	}
	
}
