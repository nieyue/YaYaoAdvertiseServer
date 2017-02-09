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

import com.nieyue.bean.AdvertiseData;
import com.nieyue.exception.StateResult;
import com.nieyue.service.AdvertiseDataService;


/**
 * 广告数据控制类
 * @author yy
 *
 */
@Controller("advertiseDataController")
@RequestMapping("/advertiseData")
public class AdvertiseDataController {
	@Resource
	private AdvertiseDataService advertiseDataService;
	
	/**
	 * 广告数据分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<AdvertiseData> browsePagingAdvertiseData(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="advertise_data_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<AdvertiseData> list = new ArrayList<AdvertiseData>();
			list= advertiseDataService.browsePagingAdvertiseData(pageNum, pageSize, orderName, orderWay);
			return list;
	}
	/**
	 * 根据广告Id和时间段广告数据分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list/advertise", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<AdvertiseData> browsePagingAdvertiseDataByAdvertiseIdAndDailyDays(
			@RequestParam(value="advertiseId")Integer advertiseId,
			@RequestParam(value="startDailyDay")Date startDailyDay,
			@RequestParam(value="endDailyDay")Date endDailyDay,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="advertise_data_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
		List<AdvertiseData> list = new ArrayList<AdvertiseData>();
		list= advertiseDataService.browsePagingAdvertiseDataByAdvertiseIdAndDailyDays(advertiseId, startDailyDay, endDailyDay, orderName, orderWay);
		return list;
	}
	/**
	 * 广告数据修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateAdvertiseData(@ModelAttribute AdvertiseData advertiseData,HttpSession session)  {
		boolean um = advertiseDataService.updateAdvertiseData(advertiseData);
		return StateResult.getSR(um);
	}
	/**
	 * 广告数据增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addAdvertiseData(@ModelAttribute AdvertiseData advertiseData, HttpSession session) {
		boolean am = advertiseDataService.addAdvertiseData(advertiseData);
		return StateResult.getSR(am);
	}
	/**
	 * 广告数据删除
	 * @return
	 */
	@RequestMapping(value = "/{advertiseDataId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delAdvertiseData(@PathVariable("advertiseDataId") Integer advertiseDataId,HttpSession session)  {
		boolean dm = advertiseDataService.delAdvertiseData(advertiseDataId);
		return StateResult.getSR(dm);
	}
	/**
	 * 广告数据浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = advertiseDataService.countAll();
		return count;
	}
	/**
	 * 广告数据单个加载
	 * @return
	 */
	@RequestMapping(value = "/{advertiseDataId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody AdvertiseData loadAdvertiseData(@PathVariable("advertiseDataId") Integer advertiseDataId,HttpSession session)  {
		AdvertiseData advertiseData=new AdvertiseData();
		if(session.getAttribute("advertiseData")!=null){
			advertiseData=(AdvertiseData) session.getAttribute("advertiseData");
		}else{
			advertiseData = advertiseDataService.loadAdvertiseData(advertiseDataId);
			 session.setAttribute("advertiseData",advertiseData);
		}
		return advertiseData;
	}
	/**
	 * 根据广告Id和时间广告数据单个加载
	 * @return
	 */
	@RequestMapping(value = "/{advertiseId}/{dailyDay}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody AdvertiseData loadAdvertiseDataByAdvertiseIdAndDailyDay(@PathVariable("advertiseId") Integer advertiseId,@PathVariable("dailyDay") Date dailyDay,HttpSession session)  {
		AdvertiseData advertiseData=new AdvertiseData();
		advertiseData = advertiseDataService.loadAdvertiseDataByAdvertiseIdAndDailyDay(advertiseId, dailyDay);
		return advertiseData;
	}
	
}
