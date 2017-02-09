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

import com.nieyue.bean.AdvertiseSpace;
import com.nieyue.exception.StateResult;
import com.nieyue.service.AdminService;
import com.nieyue.service.AdvertiseSpaceDataService;
import com.nieyue.service.AdvertiseSpaceService;
import com.nieyue.service.WaterInformationService;


/**
 * 广告位控制类
 * @author yy
 *
 */
@Controller("advertiseSpaceController")
@RequestMapping("/advertiseSpace")
public class AdvertiseSpaceController {
	@Resource
	private AdvertiseSpaceService advertiseSpaceService;
	@Resource
	private AdminService adminService;
	@Resource
	private WaterInformationService waterInformationService;
	@Resource
	private AdvertiseSpaceDataService advertiseSpaceDataService;
	
	/**
	 * 广告位分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<AdvertiseSpace> browsePagingAdvertiseSpace(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="advertise_space_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<AdvertiseSpace> list = new ArrayList<AdvertiseSpace>();
			list= advertiseSpaceService.browsePagingAdvertiseSpace(pageNum, pageSize, orderName, orderWay);
			return list;
	}
	/**
	 * 根据adminId广告位分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list/admin", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<AdvertiseSpace> browsePagingAdvertiseSpaceByAdminId(
			@RequestParam(value="adminId")Integer adminId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="advertise_space_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
		List<AdvertiseSpace> list = new ArrayList<AdvertiseSpace>();
		list= advertiseSpaceService.browsePagingAdvertiseSpaceByAdminId(adminId,pageNum, pageSize, orderName, orderWay);
		return list;
	}
	/**
	 * 广告位修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateAdvertiseSpace(@ModelAttribute AdvertiseSpace advertiseSpace,HttpSession session)  {
//		//盈利
//			AdvertiseData advertiseData = advertiseDataService.loadAdvertiseDataByAdvertiseIdAndDailyDay(advertise.getAdvertiseId(), new Date());
//			//如果没有当前的数据，增添加
//			if(advertiseData==null||advertiseData.equals("")){
//			AdvertiseData ad=new AdvertiseData();
//			ad.setAdvertiseId(advertise.getAdvertiseId());
//			ad.setPvs(1l);
//			ad.setUvs(1l);
//			ad.setIps(1l);
//			ad.setForward(0l);
//			advertiseDataService.addAdvertiseData(advertiseData);
//			
//		}else{
//			//有就更新
//			advertiseData.setPvs(1l+1);
//			advertiseData.setUvs(1l+1);
//			advertiseData.setIps(1l+1);
//			advertiseData.setForward(0l);
//			advertiseDataService.updateAdvertiseData(advertiseData);
//		}
//			
//			
//			Admin b = adminService.loadAdmin(advertiseSpace.getAdminId());
//			double nowMoney = b.getMoney()+advertiseSpace.getNowUnitMoney();//盈利金钱
//			b.setMoney(nowMoney);
//			boolean um = adminService.updateAdmin(b);
//			if(um){
//				WaterInformation waterInformation=new WaterInformation();
//				waterInformation.setAdminId(advertiseSpace.getAdminId());
//				waterInformation.setName(advertiseSpace.getName());
//				waterInformation.setType("盈利");
//				waterInformation.setMoney(advertiseSpace.getNowUnitMoney());
//				//保存流水信息
//				if(advertiseSpace.getNowUnitMoney()>0.00){					
//				waterInformationService.addWaterInformation(waterInformation);
//				}
//			}
//			//return StateResult.getSuccess();
//	
		boolean um = advertiseSpaceService.updateAdvertiseSpace(advertiseSpace);
		return StateResult.getSR(um);
	}
	/**
	 * 广告位增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addAdvertiseSpace(@ModelAttribute AdvertiseSpace advertiseSpace, HttpSession session) {
		boolean am = advertiseSpaceService.addAdvertiseSpace(advertiseSpace);
		return StateResult.getSR(am);
	}
	/**
	 * 广告位删除
	 * @return
	 */
	@RequestMapping(value = "/{advertiseSpaceId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delAdvertiseSpace(@PathVariable("advertiseSpaceId") Integer advertiseSpaceId,HttpSession session)  {
		boolean dm = advertiseSpaceService.delAdvertiseSpace(advertiseSpaceId);
		return StateResult.getSR(dm);
	}
	/**
	 * 广告位浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = advertiseSpaceService.countAll();
		return count;
	}
	/**
	 * 根据adminId广告位浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count/{adminId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAllByAdminId(@PathVariable("adminId")Integer adminId,HttpSession session)  {
		int count = advertiseSpaceService.countAllByAdminId(adminId);
		return count;
	}
	/**
	 * 广告位单个加载
	 * @return
	 */
	@RequestMapping(value = "/{advertiseSpaceId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody AdvertiseSpace loadAdvertiseSpace(@PathVariable("advertiseSpaceId") Integer advertiseSpaceId,HttpSession session)  {
		AdvertiseSpace advertiseSpace=new AdvertiseSpace();
		advertiseSpace = advertiseSpaceService.loadAdvertiseSpace(advertiseSpaceId);
		
		return advertiseSpace;
	}
	
}
