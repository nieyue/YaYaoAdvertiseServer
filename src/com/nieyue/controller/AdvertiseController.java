package com.nieyue.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nieyue.bean.Admin;
import com.nieyue.bean.Advertise;
import com.nieyue.bean.AdvertiseData;
import com.nieyue.bean.AdvertiseSpace;
import com.nieyue.bean.AdvertiseSpaceData;
import com.nieyue.bean.WaterInformation;
import com.nieyue.comments.IPCountUtil;
import com.nieyue.exception.StateResult;
import com.nieyue.service.AdminService;
import com.nieyue.service.AdvertiseDataService;
import com.nieyue.service.AdvertiseService;
import com.nieyue.service.AdvertiseSpaceDataService;
import com.nieyue.service.AdvertiseSpaceService;
import com.nieyue.service.WaterInformationService;
import com.nieyue.util.DateUtil;
import com.nieyue.util.FileUploadUtil;
import com.nieyue.util.UploaderPath;


/**
 * 广告控制类
 * @author yy
 *
 */
@Controller("advertiseController")
@RequestMapping("/advertise")
public class AdvertiseController {
	@Resource
	private AdvertiseService advertiseService;
	@Resource
	private AdvertiseDataService advertiseDataService;
	@Resource
	private AdvertiseSpaceService advertiseSpaceService;
	@Resource
	private AdvertiseSpaceDataService advertiseSpaceDataService;
	@Resource
	private AdminService adminService;
	@Resource
	private WaterInformationService waterInformationService;
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 广告分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Advertise> browsePagingAdvertise(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="advertise_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Advertise> list = new ArrayList<Advertise>();
			list= advertiseService.browsePagingAdvertise(pageNum, pageSize, orderName, orderWay);
			return list;
	}
	/**
	 * 根据adminId广告分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list/admin", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Advertise> browsePagingAdvertiseByAdminId(
			@RequestParam(value="adminId")Integer adminId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="advertise_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
		List<Advertise> list = new ArrayList<Advertise>();
		list= advertiseService.browsePagingAdvertiseByAdminId(adminId, pageNum, pageSize, orderName, orderWay);
		return list;
	}
	/**
	 * 广告修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateAdvertise(@ModelAttribute Advertise advertise,HttpSession session)  {
		if(advertise.getUnitPrice()<0.3){
			return StateResult.getSlefSR("40002", "单价小于0.3");
		}
		Advertise daoadvertise = advertiseService.loadAdvertise(advertise.getAdvertiseId());
		if(daoadvertise.getStatus().equals("已结束")){
			return StateResult.getFail();
		}
		if(daoadvertise.getStatus().equals("暂停")){
			//如果本来已经暂停，转为投放中
			if(advertise.getStatus().equals("投放中")){
				boolean um = advertiseService.updateAdvertise(advertise);
				return StateResult.getSR(um);
			}
			//如果本来已经暂停，没有数据变化
			return StateResult.getSlefSR("40002", "暂停中");
		}
		if(daoadvertise.getStatus().equals("投放中")){
			//如果本来已经投放中，转为暂停
			if(advertise.getStatus().equals("暂停")){
				boolean um = advertiseService.updateAdvertise(advertise);
				return StateResult.getSR(um);
			}
			//如果本来已经投放中，放行
		}	
		boolean um = advertiseService.updateAdvertise(advertise);
		return StateResult.getSR(um);
	}
	/**
	 * 广告点击
	 * @return
	 */
	@RequestMapping(value = "/click", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult clickAdvertise(
			@RequestParam("advertiseId") Integer advertiseId,
			@RequestParam("advertiseSpaceId") Integer advertiseSpaceId,
			@RequestParam("uv") Integer uv,
			HttpSession session,HttpServletRequest request)  {
		boolean isSuccess=false;
		boolean advertisespacePay=false;//默认广告位不计费
		boolean advertisePay=false;//默认广告不计费
		Advertise daoadvertise = advertiseService.loadAdvertise(advertiseId);
		if(daoadvertise.getStatus().equals("已结束")||daoadvertise.getStatus().equals("暂停")){
			return StateResult.getFail();
		}
		//广告消耗完或者时间到结算
		if(daoadvertise.getEndDeliveryDate().before(new Date())||
				(!daoadvertise.getNowUnitDeliveryNumber().equals(0) && (daoadvertise.getNowUnitDeliveryNumber()>=daoadvertise.getUnitDeliveryNumber()))){
			daoadvertise.setStatus("已结束");
			advertiseService.updateAdvertise(daoadvertise);
			return StateResult.getFail();
		}
		if(daoadvertise.getStatus().equals("投放中")){
			/**
			 * 广告位
			 */
			AdvertiseSpace daoadvertisespace = advertiseSpaceService.loadAdvertiseSpace(advertiseSpaceId);
			//daoadvertisespace.setNowUnitDeliveryNumber(daoadvertisespace.getNowUnitDeliveryNumber()+1);
			//daoadvertisespace.setNowUnitMoney(daoadvertisespace.getNowUnitDeliveryNumber()*daoadvertisespace.getUnitPrice());
			//isSuccess = advertiseSpaceService.updateAdvertiseSpace(daoadvertisespace);
			/**
			 * 
			 *广告位数据增加、更新
			 */
			AdvertiseSpaceData advertiseSpaceData = advertiseSpaceDataService.loadAdvertiseSpaceDataByAdvertiseSpaceIdAndDailyDay(daoadvertisespace.getAdvertiseSpaceId(), new Date());
			//如果没有当前的数据，增添加
			if(advertiseSpaceData==null||advertiseSpaceData.equals("")){
				advertisespacePay=true;
				daoadvertisespace.setNowUnitDeliveryNumber(daoadvertisespace.getNowUnitDeliveryNumber()+1);
				
				AdvertiseSpaceData ads=new AdvertiseSpaceData();
				ads.setAdvertiseSpaceId(daoadvertisespace.getAdvertiseSpaceId());
				//ads.setPvs(1l);
				ads.setUvs(1l);
				
				
				ads.setIps(1l);
				ads.setForward(0l);
			advertiseSpaceDataService.addAdvertiseSpaceData(ads);
			//redis没有就先存储ip地址
			//String[] advertiseSpaceDataIps={IPCountUtil.getIpAddr(request)};
			//JSONArray ja2=JSONArray.fromObject(advertiseSpaceDataIps);
			//stringRedisTemplate.boundValueOps("advertiseSpaceDataIps"+advertiseSpaceData.getAdvertiseSpaceDataId()).set(ja2.toString(), DateUtil.currentToEndTime(), TimeUnit.SECONDS);
			BoundSetOperations<String, String> bso2 = stringRedisTemplate.boundSetOps("advertiseSpaceDataIps"+ads.getAdvertiseSpaceDataId());
			bso2.expire(DateUtil.currentToEndTime(), TimeUnit.SECONDS);
			bso2.add(IPCountUtil.getIpAddr(request));
			
		}else{
			//有就更新
			//advertiseSpaceData.setPvs(advertiseSpaceData.getPvs()+1);
			if(uv==1){
			advertiseSpaceData.setUvs(advertiseSpaceData.getUvs()+1);
			}
			//redis有就更新
//			if(stringRedisTemplate.boundValueOps("advertiseSpaceDataIps"+advertiseSpaceData.getAdvertiseSpaceDataId()).get()!=null){
//				JSONArray nja2=JSONArray.fromObject(stringRedisTemplate.boundValueOps("advertiseSpaceDataIps"+advertiseSpaceData.getAdvertiseSpaceDataId()).get());
//				//判断有无相等的ip
//				boolean haveSample2=false;
//				for (int i = 0; i < nja2.size(); i++) {
//					String ip2 = (String) nja2.get(i);
//					if(ip2.equals(IPCountUtil.getIpAddr(request))){
//						haveSample2=true;//有相等的
//					}
//				}
//				if(!haveSample2){
//					nja2.add(IPCountUtil.getIpAddr(request));
//					advertiseSpaceData.setIps(advertiseSpaceData.getIps()+1);
//				}
//			}
			BoundSetOperations<String, String> bso2 = stringRedisTemplate.boundSetOps("advertiseSpaceDataIps"+advertiseSpaceData.getAdvertiseSpaceDataId());
			if(bso2.members()!=null && bso2.members().size()>0){
					int oldSize = bso2.members().size();
				 bso2.add(IPCountUtil.getIpAddr(request));
					int newSize = bso2.members().size();
					advertiseSpaceData.setIps(Long.valueOf(newSize));
					//ip增加了
					if(oldSize<newSize){
						advertisespacePay=true;
						daoadvertisespace.setNowUnitDeliveryNumber(daoadvertisespace.getNowUnitDeliveryNumber()+1);	
					}
			}else{
				bso2.expire(DateUtil.currentToEndTime(), TimeUnit.SECONDS);
				bso2.add(IPCountUtil.getIpAddr(request));
			}
			advertiseSpaceData.setForward(advertiseSpaceData.getForward()+0l);
			boolean bb = advertiseSpaceDataService.updateAdvertiseSpaceData(advertiseSpaceData);
			
		}
			if(advertisespacePay && daoadvertise.getBillingMode().equals("CPC")){
				/**
				 * 广告位金钱更新
				 */
				daoadvertisespace.setNowUnitMoney(daoadvertisespace.getNowUnitDeliveryNumber()*daoadvertisespace.getUnitPrice());
				isSuccess = advertiseSpaceService.updateAdvertiseSpace(daoadvertisespace);				
				/**
				 * 网站主金钱更新、流水增加
				 */
				Admin adminadvertisespace= adminService.loadAdmin(daoadvertisespace.getAdminId());
				double nowMoney2 = adminadvertisespace.getMoney()+daoadvertisespace.getNowUnitMoney();//盈利金钱
				adminadvertisespace.setMoney(nowMoney2);
				boolean um2 = adminService.updateAdmin(adminadvertisespace);
				//代理费用
				if(!adminadvertisespace.getParentId().equals(0)){
					Admin parentadminadvertisespace = adminService.loadAdmin(adminadvertisespace.getParentId());
					double nowMoney22 = parentadminadvertisespace.getMoney()+(daoadvertisespace.getNowUnitDeliveryNumber()/1000+1)*0.25;//佣金金钱
					parentadminadvertisespace.setMoney(nowMoney22);
					adminService.updateAdmin(parentadminadvertisespace);
				}
				if(um2){
					WaterInformation daowaterinformation2 = waterInformationService.loadWaterInformationByAdminIdAndCreateDate(daoadvertisespace.getAdminId(), new Date());
					//不同天增加。
					if(daowaterinformation2==null || daowaterinformation2.equals("")){
						WaterInformation waterInformation2=new WaterInformation();
						waterInformation2.setAdminId(daoadvertisespace.getAdminId());
						waterInformation2.setName(daoadvertisespace.getName());
						waterInformation2.setType("盈利");
						waterInformation2.setMoney(daoadvertisespace.getUnitPrice());
						//保存流水信息
						if(daoadvertisespace.getNowUnitMoney()>0.00){					
							waterInformationService.addWaterInformation(waterInformation2);
						}
					}else{
						//同一天更新
						daowaterinformation2.setName(daoadvertisespace.getName());
						daowaterinformation2.setType("盈利");
						daowaterinformation2.setMoney(daowaterinformation2.getMoney()+daoadvertisespace.getUnitPrice());
						waterInformationService.updateWaterInformation(daowaterinformation2);
					}
					WaterInformation daowaterinformation22 = waterInformationService.loadWaterInformationByAdminIdAndCreateDate(adminadvertisespace.getParentId(), new Date());
					//不同天增加。
					if(daowaterinformation22==null || daowaterinformation22.equals("")){
						WaterInformation waterInformation22=new WaterInformation();
						waterInformation22.setAdminId(adminadvertisespace.getParentId());
						waterInformation22.setName(daoadvertisespace.getName());
						waterInformation22.setType("获取佣金");
						waterInformation22.setMoney((daoadvertisespace.getNowUnitDeliveryNumber()/1000+1)*0.25);
						//保存流水信息
						if(daoadvertisespace.getNowUnitMoney()>0.00){					
							waterInformationService.addWaterInformation(waterInformation22);
						}
					}else{
						//同一天更新
						daowaterinformation22.setName(daoadvertisespace.getName());
						daowaterinformation22.setType("获取佣金");
						daowaterinformation22.setMoney(daowaterinformation22.getMoney()+(daoadvertisespace.getNowUnitDeliveryNumber()/1000+1)*0.25);
						waterInformationService.updateWaterInformation(daowaterinformation22);
					}
				}
			}
			
		
			
			
			
			/**
			 * 广告金钱更新
			 */
			//daoadvertise.setNowUnitDeliveryNumber(daoadvertise.getNowUnitDeliveryNumber()+1);
			//daoadvertise.setNowUnitMoney(daoadvertise.getNowUnitDeliveryNumber()*daoadvertise.getUnitPrice());
			//isSuccess = advertiseService.updateAdvertise(daoadvertise);
			
			/**
			 * 
			 *广告数据增加、更新
			 */
			AdvertiseData advertiseData = advertiseDataService.loadAdvertiseDataByAdvertiseIdAndDailyDay(daoadvertise.getAdvertiseId(), new Date());
			//如果没有当前的数据，增添加
			if(advertiseData==null||advertiseData.equals("")){
				advertisePay=true;
			AdvertiseData ad=new AdvertiseData();
			ad.setAdvertiseId(daoadvertise.getAdvertiseId());
			//ad.setPvs(1l);
			ad.setUvs(1l);
			daoadvertise.setNowUnitDeliveryNumber(daoadvertise.getNowUnitDeliveryNumber()+1);	
			ad.setIps(1l);
			ad.setForward(0l);
			advertiseDataService.addAdvertiseData(ad);
			//redis没有就先存储ip地址
//			String[] advertiseDataIps={IPCountUtil.getIpAddr(request)};
//			JSONArray ja=JSONArray.fromObject(advertiseDataIps);
//			stringRedisTemplate.boundValueOps("advertiseDataIps"+advertiseData.getAdvertiseDataId()).set(ja.toString(), DateUtil.currentToEndTime(), TimeUnit.SECONDS);
			
			BoundSetOperations<String, String> bso = stringRedisTemplate.boundSetOps("advertiseDataIps"+ad.getAdvertiseDataId());
			bso.expire(DateUtil.currentToEndTime(), TimeUnit.SECONDS);
			bso.add(IPCountUtil.getIpAddr(request));
			
			}else{
				//有就更新
				//advertiseData.setPvs(advertiseData.getPvs()+1);
				if(uv==1){
				advertiseData.setUvs(advertiseData.getUvs()+1);
				}
				//redis有就更新
//				if(stringRedisTemplate.boundValueOps("advertiseDataIps"+advertiseData.getAdvertiseDataId()).get()!=null){
//					JSONArray nja=JSONArray.fromObject(stringRedisTemplate.boundValueOps("advertiseSpaceDataIps"+advertiseData.getAdvertiseDataId()).get());
//					//判断有无相等的ip
//					boolean haveSample=false;
//					for (int i = 0; i < nja.size(); i++) {
//						String ip = (String) nja.get(i);
//						if(ip.equals(IPCountUtil.getIpAddr(request))){
//							haveSample=true;//有相等的
//						}
//					}
//					if(!haveSample){
//						nja.add(IPCountUtil.getIpAddr(request));
//						advertiseData.setIps(advertiseData.getIps()+1);
//					}
//				}
				BoundSetOperations<String, String> bso = stringRedisTemplate.boundSetOps("advertiseDataIps"+advertiseData.getAdvertiseDataId());
				if(bso.members()!=null && bso.members().size()>0){
					int oldSize = bso.members().size();
						bso.add(IPCountUtil.getIpAddr(request));
					int newSize = bso.members().size();
						advertiseData.setIps(Long.valueOf(newSize));
							//ip增加了
							if(oldSize<newSize){
								advertisePay=true;
								daoadvertise.setNowUnitDeliveryNumber(daoadvertise.getNowUnitDeliveryNumber()+1);
							}
				}else{
					bso.expire(DateUtil.currentToEndTime(), TimeUnit.SECONDS);
					bso.add(IPCountUtil.getIpAddr(request));
				}
				advertiseData.setForward(advertiseData.getForward()+0l);
				advertiseDataService.updateAdvertiseData(advertiseData);
			}
			if(advertisePay && daoadvertise.getBillingMode().equals("CPC")){
			/**
			 * 广告金钱更新
			 */
			daoadvertise.setNowUnitMoney(daoadvertise.getNowUnitDeliveryNumber()*daoadvertise.getUnitPrice());
			isSuccess = advertiseService.updateAdvertise(daoadvertise);
			/**
			 * 广告主主金钱更新、流水增加
			 */
			Admin adminadvertise = adminService.loadAdmin(daoadvertise.getAdminId());
			double nowMoney = adminadvertise.getMoney()-daoadvertise.getNowUnitMoney();//消耗金钱
			adminadvertise.setMoney(nowMoney);
			 boolean um = adminService.updateAdmin(adminadvertise);
			//代理费用
				if(!adminadvertise.getParentId().equals(0)){
					Admin parentadminadvertise = adminService.loadAdmin(adminadvertise.getParentId());
					double nowMoney11 = parentadminadvertise.getMoney()+(daoadvertise.getNowUnitDeliveryNumber()/1000+1)*0.25;//佣金金钱
					parentadminadvertise.setMoney(nowMoney11);
					adminService.updateAdmin(parentadminadvertise);
				}
			if(um){
				WaterInformation daowaterinformation = waterInformationService.loadWaterInformationByAdminIdAndCreateDate(daoadvertise.getAdminId(), new Date());
				//不同天增加。
				if(daowaterinformation==null || daowaterinformation.equals("")){
					WaterInformation waterInformation=new WaterInformation();
					waterInformation.setAdminId(daoadvertise.getAdminId());
					waterInformation.setName(daoadvertise.getName());
					waterInformation.setType("消耗");
					waterInformation.setMoney(daoadvertise.getNowUnitMoney());
					//保存流水信息
					if(daoadvertise.getNowUnitMoney()>0.00){					
						waterInformationService.addWaterInformation(waterInformation);
					}
				}else{
					//同一天更新
					daowaterinformation.setName(daoadvertise.getName());
					daowaterinformation.setType("消耗");
					daowaterinformation.setMoney(daowaterinformation.getMoney()+daoadvertise.getUnitPrice());
					waterInformationService.updateWaterInformation(daowaterinformation);
				}
				WaterInformation daowaterinformation11 = waterInformationService.loadWaterInformationByAdminIdAndCreateDate(adminadvertise.getParentId(), new Date());
				//代理费用
				if(daowaterinformation11==null || daowaterinformation11.equals("")){

					WaterInformation waterInformation11=new WaterInformation();
					waterInformation11.setAdminId(adminadvertise.getParentId());
					waterInformation11.setName(daoadvertise.getName());
					waterInformation11.setType("获取佣金");
					waterInformation11.setMoney((daoadvertise.getNowUnitDeliveryNumber()/1000+1)*0.25);
					//保存流水信息
					if(daoadvertise.getNowUnitMoney()>0.00){					
						waterInformationService.addWaterInformation(waterInformation11);
					}
				}else{
					daowaterinformation11.setName(daoadvertise.getName());
					daowaterinformation11.setType("获取佣金");
					daowaterinformation11.setMoney(daowaterinformation11.getMoney()+(daoadvertise.getNowUnitDeliveryNumber()/1000+1)*0.25);
					waterInformationService.updateWaterInformation(daowaterinformation11);
					
				}
			}
			}
			
		}
		return StateResult.getSR(isSuccess);
	}
	/**
	 * 广告位展示广告
	 * @return
	 */
	@RequestMapping(value = "/advertiseSpaceShowAdvertise", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Advertise advertiseSpaceShowAdvertise(@RequestParam("advertiseSpaceId") Integer advertiseSpaceId,HttpSession session)  {
		AdvertiseSpace advertiseSpace = advertiseSpaceService.loadAdvertiseSpace(advertiseSpaceId);
		String platform = advertiseSpace.getPlatform();
		String businessType = advertiseSpace.getBusinessType();
		String billingMode = advertiseSpace.getBillingMode();
		Double unitPrice = advertiseSpace.getUnitPrice();
		String region = advertiseSpace.getRegion();
		Double needUnitPrice=0.00;//展示此等价位以上的广告
		String advertiseStatus="投放中";
		if(billingMode.equals("CPM")){
			needUnitPrice=unitPrice+1;
		}
		if(billingMode.equals("CPC")){
			needUnitPrice=unitPrice+0.1;
		}
		String status = advertiseSpace.getStatus();
		if(!status.equals("正常")){
			return null;
		}
		Advertise advertise=new Advertise();
		 advertise = advertiseService.browsePagingAdvertiseSpaceShowAdvertise(platform,businessType,billingMode,region,new Double(new DecimalFormat("0.00").format(needUnitPrice)),advertiseStatus);
		int timer=1;
		List<Advertise> list=new ArrayList<Advertise>();
		while (true) {
		if(advertise==null||advertise.equals("")){
				advertise = advertiseService.browsePagingAdvertiseSpaceShowAdvertise(platform,businessType,billingMode,region,new Double(new DecimalFormat("0.00").format(needUnitPrice)),advertiseStatus);
		}else{
			break;
		}
		timer++;
		//防止无限循环
		if(timer==10){
			list = advertiseService.browsePagingAdvertiseSpaceShowAdvertiseBei(platform,businessType,billingMode,region,new Double(new DecimalFormat("0.00").format(needUnitPrice)),advertiseStatus);
			if(list.size()>0){				
			advertise=list.get(new Random().nextInt(list.size()));
			}
			break;
		}
		}
		if(advertise==null){
			return null;
		}
		//有展示就算pv
		if(advertise.getAdvertiseId()!=null&&!advertise.getAdvertiseId().equals("")){
			
			AdvertiseSpaceData advertiseSpaceData = advertiseSpaceDataService.loadAdvertiseSpaceDataByAdvertiseSpaceIdAndDailyDay(advertiseSpace.getAdvertiseSpaceId(), new Date());
			
			AdvertiseData advertiseData = advertiseDataService.loadAdvertiseDataByAdvertiseIdAndDailyDay(advertise.getAdvertiseId(), new Date());
			//如果没有当前的数据，增添加
			if(advertiseData==null||advertiseData.equals("")){
			AdvertiseData ad=new AdvertiseData();
			ad.setAdvertiseId(advertise.getAdvertiseId());
			ad.setPvs(1l);
			ad.setUvs(0l);
			ad.setIps(0l);
			ad.setForward(0l);
			ad.setDailyDay(new Date());
			if(advertise.getBillingMode().equals("CPM")){
			advertise.setNowUnitDeliveryNumber(advertise.getNowUnitDeliveryNumber()+1);	
			}
			advertiseDataService.addAdvertiseData(ad);
			}else{
				//有就更新
				advertiseData.setPvs(advertiseData.getPvs()+1);
				if(advertise.getBillingMode().equals("CPM")){
				advertise.setNowUnitDeliveryNumber(advertise.getNowUnitDeliveryNumber()+1);
				if(advertise.getNowUnitDeliveryNumber()>0){//广告主大于0算一个千次计算
					advertise.setNowUnitMoney((advertise.getNowUnitDeliveryNumber()/1000+1)*advertise.getUnitPrice());
					advertiseService.updateAdvertise(advertise);
					
					Admin adminadvertise = adminService.loadAdmin(advertise.getAdminId());
					//代理费用
					if(!adminadvertise.getParentId().equals(0)){
						Admin parentadminadvertise = adminService.loadAdmin(adminadvertise.getParentId());
						double nowMoney = parentadminadvertise.getMoney()+(advertise.getNowUnitDeliveryNumber()/1000+1)*0.25;//佣金金钱
						parentadminadvertise.setMoney(nowMoney);
						adminService.updateAdmin(parentadminadvertise);
					}
					/**
					 * 广告主主金钱更新、流水增加
					 */
					double nowMoney = adminadvertise.getMoney()-advertise.getNowUnitMoney();//消耗金钱
					adminadvertise.setMoney(nowMoney);
					 boolean um = adminService.updateAdmin(adminadvertise);
					if(um){
						WaterInformation daowaterinformation = waterInformationService.loadWaterInformationByAdminIdAndCreateDate(advertise.getAdminId(), new Date());
						//不同天增加。
						if(daowaterinformation==null || daowaterinformation.equals("")){
							WaterInformation waterInformation=new WaterInformation();
							waterInformation.setAdminId(advertise.getAdminId());
							waterInformation.setName(advertise.getName());
							waterInformation.setType("消耗");
							waterInformation.setMoney(advertise.getNowUnitMoney());
							//保存流水信息
							if(advertise.getNowUnitMoney()>0.00){					
								waterInformationService.addWaterInformation(waterInformation);
							}
							
						}else{
							//同一天更新
							daowaterinformation.setName(advertise.getName());
							daowaterinformation.setType("消耗");
							daowaterinformation.setMoney(daowaterinformation.getMoney()+advertise.getUnitPrice());
							waterInformationService.updateWaterInformation(daowaterinformation);
							
						}
						WaterInformation daowaterinformation11 = waterInformationService.loadWaterInformationByAdminIdAndCreateDate(adminadvertise.getParentId(), new Date());
						//代理费用
						if(daowaterinformation11==null || daowaterinformation11.equals("")){

							WaterInformation waterInformation11=new WaterInformation();
							waterInformation11.setAdminId(adminadvertise.getParentId());
							waterInformation11.setName(advertise.getName());
							waterInformation11.setType("获取佣金");
							waterInformation11.setMoney((advertise.getNowUnitDeliveryNumber()/1000+1)*0.25);
							//保存流水信息
							if(advertise.getNowUnitMoney()>0.00){					
								waterInformationService.addWaterInformation(waterInformation11);
							}
						}else{
							daowaterinformation11.setName(advertise.getName());
							daowaterinformation11.setType("获取佣金");
							daowaterinformation11.setMoney(daowaterinformation11.getMoney()+(advertise.getNowUnitDeliveryNumber()/1000+1)*0.25);
							waterInformationService.updateWaterInformation(daowaterinformation11);
							
						}
					}
					
				}
				}
				advertiseDataService.updateAdvertiseData(advertiseData);
			}
			
			//如果没有当前的数据，增添加
			if(advertiseSpaceData==null||advertiseSpaceData.equals("")){
				
				AdvertiseSpaceData ads=new AdvertiseSpaceData();
				ads.setAdvertiseSpaceId(advertiseSpace.getAdvertiseSpaceId());
				ads.setPvs(1l);
				ads.setUvs(0l);
				ads.setIps(0l);
				ads.setForward(0l);
				ads.setDailyDay(new Date());
				if(advertise.getBillingMode().equals("CPM")){
				advertiseSpace.setNowUnitDeliveryNumber(advertiseSpace.getNowUnitDeliveryNumber()+1);
				}
			advertiseSpaceDataService.addAdvertiseSpaceData(ads);
		}else{
			//有就更新
			advertiseSpaceData.setPvs(advertiseSpaceData.getPvs()+1);
			if(advertise.getBillingMode().equals("CPM")){
				advertiseSpace.setNowUnitDeliveryNumber(advertiseSpace.getNowUnitDeliveryNumber()+1);
				if(advertiseSpace.getNowUnitDeliveryNumber()/1000>=1){//如果超过1000次
					
					/**
					 * 广告位金钱更新
					 */
					advertiseSpace.setNowUnitMoney((advertiseSpace.getNowUnitDeliveryNumber()/1000)*advertiseSpace.getUnitPrice());//1000次计算
					 advertiseSpaceService.updateAdvertiseSpace(advertiseSpace);
					 
					/**
					 * 网站主金钱更新、流水增加
					 */
					Admin adminadvertisespace= adminService.loadAdmin(advertiseSpace.getAdminId());
					//代理费用
					if(!adminadvertisespace.getParentId().equals(0)){
						Admin parentadminadvertisespace = adminService.loadAdmin(adminadvertisespace.getParentId());
						double nowMoney = parentadminadvertisespace.getMoney()+(advertiseSpace.getNowUnitDeliveryNumber()/1000+1)*0.25;//佣金金钱
						parentadminadvertisespace.setMoney(nowMoney);
						adminService.updateAdmin(parentadminadvertisespace);
					}
					double nowMoney2 = adminadvertisespace.getMoney()+advertiseSpace.getNowUnitMoney();//盈利金钱
					adminadvertisespace.setMoney(nowMoney2);
					boolean um2 = adminService.updateAdmin(adminadvertisespace);
					if(um2){
						WaterInformation daowaterinformation2 = waterInformationService.loadWaterInformationByAdminIdAndCreateDate(advertiseSpace.getAdminId(), new Date());
						//不同天增加。
						if(daowaterinformation2==null || daowaterinformation2.equals("")){
							WaterInformation waterInformation2=new WaterInformation();
							waterInformation2.setAdminId(advertiseSpace.getAdminId());
							waterInformation2.setName(advertiseSpace.getName());
							waterInformation2.setType("盈利");
							waterInformation2.setMoney(advertiseSpace.getUnitPrice());
							//保存流水信息
							if(advertiseSpace.getNowUnitMoney()>0.00){					
								waterInformationService.addWaterInformation(waterInformation2);
							}
						}else{
							//同一天更新
							daowaterinformation2.setName(advertiseSpace.getName());
							daowaterinformation2.setType("盈利");
							daowaterinformation2.setMoney(daowaterinformation2.getMoney()+advertiseSpace.getUnitPrice());
							waterInformationService.updateWaterInformation(daowaterinformation2);
						}
						WaterInformation daowaterinformation22 = waterInformationService.loadWaterInformationByAdminIdAndCreateDate(adminadvertisespace.getParentId(), new Date());
						//不同天增加。
						if(daowaterinformation22==null || daowaterinformation22.equals("")){
							WaterInformation waterInformation22=new WaterInformation();
							waterInformation22.setAdminId(adminadvertisespace.getParentId());
							waterInformation22.setName(advertiseSpace.getName());
							waterInformation22.setType("获取佣金");
							waterInformation22.setMoney((advertiseSpace.getNowUnitDeliveryNumber()/1000+1)*0.25);
							//保存流水信息
							if(advertiseSpace.getNowUnitMoney()>0.00){					
								waterInformationService.addWaterInformation(waterInformation22);
							}
						}else{
							//同一天更新
							daowaterinformation22.setName(advertiseSpace.getName());
							daowaterinformation22.setType("获取佣金");
							daowaterinformation22.setMoney(daowaterinformation22.getMoney()+(advertiseSpace.getNowUnitDeliveryNumber()/1000+1)*0.25);
							waterInformationService.updateWaterInformation(daowaterinformation22);
						}
					}	
				}
			}
			advertiseSpaceDataService.updateAdvertiseSpaceData(advertiseSpaceData);
			
		}
		}
		
		return advertise;
	}
	/**
	 * 广告增加
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addAdvertise(@ModelAttribute Advertise advertise, HttpSession session) {
			if(advertise.getUnitPrice()<0.3){
				return StateResult.getFail();
			}
		if(advertise.getEndDeliveryDate().before(new Date())){
					return StateResult.getFail();
				}
	
		boolean am = advertiseService.addAdvertise(advertise);
		return StateResult.getSR(am);
	}
	/**
	 * 广告删除
	 * @return
	 */
	@RequestMapping(value = "/{advertiseId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delAdvertise(@PathVariable("advertiseId") Integer advertiseId,HttpSession session)  {
		boolean dm = advertiseService.delAdvertise(advertiseId);
		return StateResult.getSR(dm);
	}
	/**
	 * 广告浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="adminId",required=false)Integer adminId,
			@RequestParam(value="type",required=false)String type,
			@RequestParam(value="subtype",required=false) String subtype,
			@RequestParam(value="billingMode",required=false) String billingMode,
			@RequestParam(value="region",required=false) String region,
			HttpSession session)  {
		int count =advertiseService.countAll(adminId, type, subtype, billingMode,region);
		return count;
	}
	/**
	 * 广告单个加载
	 * @return
	 */
	@RequestMapping(value = "/{advertiseId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Advertise loadAdvertise(@PathVariable("advertiseId") Integer advertiseId,HttpSession session)  {
		Advertise advertise=new Advertise();
		advertise = advertiseService.loadAdvertise(advertiseId);
		return advertise;
	}
	/**
	 * 图片修改
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/img/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String updateAdvertiseImg(
			HttpSession session,
			@RequestParam("img") MultipartFile file,
			@RequestParam("oldImg")String oldImg) throws IOException  {
		String imgUrl = null;
		String imgdir=DateUtil.getImgDir();
		try{
			imgUrl = FileUploadUtil.updateFormDataMerImgFileUpload(file, session,UploaderPath.GetValueByKey(UploaderPath.ROOTPATH),UploaderPath.GetValueByKey(UploaderPath.IMG), imgdir, oldImg);
		}catch (IOException e) {
			throw new IOException();
		}
		return imgUrl;
	}
	/**
	 * 图片增加
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/img/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String addAdvertiseImg(
			@RequestParam("img") MultipartFile file,
			HttpSession session ) throws IOException  {
		String imgUrl = null;
		String imgdir=DateUtil.getImgDir();
		try{
			imgUrl = FileUploadUtil.FormDataMerImgFileUpload(file, session,UploaderPath.GetValueByKey(UploaderPath.ROOTPATH),UploaderPath.GetValueByKey(UploaderPath.IMG),imgdir);
		}catch (IOException e) {
			throw new IOException();
		}
		return imgUrl;
	}
}
