package com.nieyue.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.nieyue.bean.AdvertiseSpaceData;
import com.nieyue.exception.StateResult;
import com.nieyue.service.AdvertiseSpaceDataService;


/**
 * 广告位数据控制类
 * @author yy
 *
 */
@Controller("advertiseSpaceDataController")
@RequestMapping("/advertiseSpaceData")
public class AdvertiseSpaceDataController {
	@Resource
	private AdvertiseSpaceDataService advertiseSpaceDataService;
	
	/**
	 * 广告位数据分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<AdvertiseSpaceData> browsePagingAdvertiseSpaceData(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="advertise_space_data_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<AdvertiseSpaceData> list = new ArrayList<AdvertiseSpaceData>();
			list= advertiseSpaceDataService.browsePagingAdvertiseSpaceData(pageNum, pageSize, orderName, orderWay);
			return list;
	}
	/**
	 * 根据广告位Id和时间段广告数据分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list/advertiseSpace", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<AdvertiseSpaceData> browsePagingAdvertiseSpaceDataByAdvertiseSpaceIdAndDailyDays(
			@RequestParam(value="advertiseSpaceId")Integer advertiseSpaceId,
			@RequestParam(value="startDailyDay")Date startDailyDay,
			@RequestParam(value="endDailyDay")Date endDailyDay,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="advertise_space_data_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
		List<AdvertiseSpaceData> list = new ArrayList<AdvertiseSpaceData>();
		list= advertiseSpaceDataService.browsePagingAdvertiseSpaceDataByAdvertiseSpaceIdAndDailyDays(advertiseSpaceId, startDailyDay, endDailyDay, orderName, orderWay);
		return list;
	}
	/**
	 * 广告位数据修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateAdvertiseSpaceData(@ModelAttribute AdvertiseSpaceData advertiseSpaceData,HttpSession session)  {
		boolean um = advertiseSpaceDataService.updateAdvertiseSpaceData(advertiseSpaceData);
		return StateResult.getSR(um);
	}
	/**
	 * 广告位数据增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addAdvertiseSpaceData(@ModelAttribute AdvertiseSpaceData advertiseSpaceData, HttpSession session) {
		boolean am = advertiseSpaceDataService.addAdvertiseSpaceData(advertiseSpaceData);
		return StateResult.getSR(am);
	}
	/**
	 * 广告位数据删除
	 * @return
	 */
	@RequestMapping(value = "/{AdvertiseSpaceDataId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delAdvertiseSpaceData(@PathVariable("AdvertiseSpaceDataId") Integer advertiseSpaceDataId,HttpSession session)  {
		boolean dm = advertiseSpaceDataService.delAdvertiseSpaceData(advertiseSpaceDataId);
		return StateResult.getSR(dm);
	}
	/**
	 * 广告位数据浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = advertiseSpaceDataService.countAll();
		return count;
	}
	/**
	 * 广告位数据单个加载
	 * @return
	 */
	@RequestMapping(value = "/{advertiseSpaceDataId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody AdvertiseSpaceData loadAdvertiseSpaceData(@PathVariable("advertiseSpaceDataId") Integer advertiseSpaceDataId,HttpSession session)  {
		AdvertiseSpaceData advertiseSpaceData=new AdvertiseSpaceData();
		if(session.getAttribute("AdvertiseSpaceData")!=null){
			advertiseSpaceData=(AdvertiseSpaceData) session.getAttribute("advertiseSpaceData");
		}else{
			advertiseSpaceData = advertiseSpaceDataService.loadAdvertiseSpaceData(advertiseSpaceDataId);
			 session.setAttribute("advertiseSpaceData",advertiseSpaceData);
		}
		return advertiseSpaceData;
	}
	/**
	 * 根据广告位Id和时间广告数据单个加载
	 * @return
	 */
	@RequestMapping(value = "/{advertiseSpaceId}/{dailyDay}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody AdvertiseSpaceData loadAdvertiseSpaceDataByAdvertiseSpaceIdAndDailyDay(@PathVariable("advertiseSpaceId") Integer advertiseSpaceId,@PathVariable("dailyDay") Date dailyDay,HttpSession session)  {
		AdvertiseSpaceData advertiseSpaceData=new AdvertiseSpaceData();
		advertiseSpaceData = advertiseSpaceDataService.loadAdvertiseSpaceDataByAdvertiseSpaceIdAndDailyDay(advertiseSpaceId, dailyDay);
		return advertiseSpaceData;
	}
}
