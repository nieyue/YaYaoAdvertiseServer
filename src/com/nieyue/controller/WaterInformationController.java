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

import com.nieyue.bean.WaterInformation;
import com.nieyue.exception.StateResult;
import com.nieyue.service.WaterInformationService;


/**
 * 流水信息控制类
 * @author yy
 *
 */
@Controller("waterInformationController")
@RequestMapping("/waterInformation")
public class WaterInformationController {
	@Resource
	private WaterInformationService waterInformationService;
	
	/**
	 * 流水信息分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<WaterInformation> browsePagingWaterInformation(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="water_information_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<WaterInformation> list = new ArrayList<WaterInformation>();
			list= waterInformationService.browsePagingWaterInformation(pageNum, pageSize, orderName, orderWay);
			return list;
	}
	/**
	 * 根据adminId流水信息分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list/admin", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<WaterInformation> browsePagingAdvertiseByAdminId(
			@RequestParam(value="adminId")Integer adminId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="water_information_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
		List<WaterInformation> list = new ArrayList<WaterInformation>();
		list= waterInformationService.browsePagingWaterInformationByAdminId(adminId, pageNum, pageSize, orderName, orderWay);
		return list;
	}
	/**
	 * 流水信息修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateWaterInformation(@ModelAttribute WaterInformation waterInformation,HttpSession session)  {
		
		boolean um = waterInformationService.updateWaterInformation(waterInformation);
		return StateResult.getSR(um);
	}
	/**
	 * 流水信息增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addWaterInformation(@ModelAttribute WaterInformation waterInformation, HttpSession session) {
		boolean am = waterInformationService.addWaterInformation(waterInformation);
		return StateResult.getSR(am);
	}
	/**
	 * 流水信息删除
	 * @return
	 */
	@RequestMapping(value = "/{waterInformationId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delWaterInformation(@PathVariable("waterInformationId") Integer waterInformationId,HttpSession session)  {
		boolean dm = waterInformationService.delWaterInformation(waterInformationId);
		return StateResult.getSR(dm);
	}
	/**
	 * 流水信息浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = waterInformationService.countAll();
		return count;
	}
	/**
	 * 根据adminId流水信息浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count/{adminId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAllByAdminId(@PathVariable("adminId") Integer adminId,HttpSession session)  {
		int count =waterInformationService.countAllByAdminId(adminId);
		return count;
	}
	/**
	 * 流水信息单个加载
	 * @return
	 */
	@RequestMapping(value = "/{waterInformationId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody WaterInformation loadWaterInformation(@PathVariable("waterInformationId") Integer waterInformationId,HttpSession session)  {
		WaterInformation waterInformation=new WaterInformation();
		if(session.getAttribute("waterInformation")!=null){
			waterInformation=(WaterInformation) session.getAttribute("waterInformation");
		}else{
			waterInformation = waterInformationService.loadWaterInformation(waterInformationId);
			 session.setAttribute("waterInformation",waterInformation);
		}
		return waterInformation;
	}
	/**
	 * 根据adminId 和创建日期流水信息单个加载
	 * @return
	 */
	@RequestMapping(value = "/{adminId}/{createDate}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody WaterInformation loadWaterInformationByAdminIdAndCreateDate(@PathVariable("adminId") Integer adminId,@PathVariable("createDate") Date createDate,HttpSession session)  {
		WaterInformation waterInformation=new WaterInformation();
			waterInformation = waterInformationService.loadWaterInformationByAdminIdAndCreateDate(adminId, createDate);
		return waterInformation;
	}
	
}
