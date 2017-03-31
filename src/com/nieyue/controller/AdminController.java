package com.nieyue.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nieyue.bean.Admin;
import com.nieyue.bean.Role;
import com.nieyue.bean.WaterInformation;
import com.nieyue.exception.StateResult;
import com.nieyue.mail.SendMailDemo;
import com.nieyue.service.AdminService;
import com.nieyue.service.RoleService;
import com.nieyue.service.WaterInformationService;
import com.nieyue.token.TokenManager;
import com.nieyue.token.TokenModel;
import com.nieyue.util.DateUtil;
import com.nieyue.util.MyDESutil;
import com.nieyue.util.MyValidator;
import com.nieyue.util.NumberUtil;
import com.nieyue.util.StatusCode;
import com.nieyue.util.ThirdParty;
import com.yayao.messageinterface.SMSInterface;


/**
 * 管理员控制类
 * @author yy
 *
 */
@Controller("adminController")
@RequestMapping("/admin")
public class AdminController {
	@Resource
	private AdminService adminService;
	@Autowired
	private TokenManager tokenManager;
	@Autowired
	private RoleService roleService;
	@Autowired
	private WaterInformationService waterInformationService;
	
	/**
	 * 管理员分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Admin> browsePagingAdmin(
			@RequestParam(value="roleId",required=false)Integer roleId,
			@RequestParam(value="parentId",required=false)Integer parentId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="admin_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Admin> list = new ArrayList<Admin>();
			list= adminService.browsePagingAdmin(roleId,parentId,pageNum, pageSize, orderName, orderWay);
			return list;
	}
	/**
	 * 管理员全部查询
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list/all", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Admin> browseAllAdmin(
			@RequestParam(value="orderName",required=false,defaultValue="admin_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
		List<Admin> list = new ArrayList<Admin>();
		list= adminService.browseAllAdmin( orderName, orderWay);
		return list;
	}
	/**
	 * 管理管理员修改
	 * @return
	 */
	@RequestMapping(value = "/update/all", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateAdminAll(@ModelAttribute Admin admin,HttpSession session)  {
		//不能修改金钱
		if(!adminService.loadAdmin(admin.getAdminId()).getMoney().equals(admin.getMoney())){
			return StateResult.getSR(false);
		}
		//不能添加相同的手机号或者邮箱
		List<String> lp = adminService.browseAllAdminPhone();
		List<String> le = adminService.browseAllAdminEmail();
		lp.removeAll(Collections.singleton(null));
		le.removeAll(Collections.singleton(null));
		Admin oa = adminService.loadAdmin(admin.getAdminId());//自身原来的
		//如果修改的不等于原来的，才验证
		if(oa.getCellPhone()!=null&&!oa.getCellPhone().equals(admin.getCellPhone())){
			for (int i = 0; i < lp.size(); i++) {
				if(lp.get(i).equals(admin.getCellPhone())){
					return StateResult.getSlefSR(40002, "手机号已经存在");
				}
			}
		}
		if(oa.getEmail()!=null&&!oa.getEmail().equals(admin.getEmail())){
		for (int i = 0; i < le.size(); i++) {
			if(le.get(i).equals(admin.getEmail())){
				return StateResult.getSlefSR(40002, "email已经存在");
			}
		}
		}
		admin.setPassword(MyDESutil.getMD5(admin.getPassword()));
		boolean um = adminService.updateAdmin(admin);
		return StateResult.getSR(um);
	}
	/**
	 * 管理员修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateAdmin(@ModelAttribute Admin admin,HttpSession session)  {
		//不能修改金钱
		if(!adminService.loadAdmin(admin.getAdminId()).getMoney().equals(admin.getMoney())){
			return StateResult.getSR(false);
		}
		//不能添加相同的手机号或者邮箱
		List<String> lp = adminService.browseAllAdminPhone();
		List<String> le = adminService.browseAllAdminEmail();
		lp.removeAll(Collections.singleton(null));
		le.removeAll(Collections.singleton(null));
		Admin oa = adminService.loadAdmin(admin.getAdminId());//自身原来的
		//如果修改的不等于原来的，才验证
		if(oa.getCellPhone()!=null&&!oa.getCellPhone().equals(admin.getCellPhone())){
			for (int i = 0; i < lp.size(); i++) {
				if(lp.get(i).equals(admin.getCellPhone())){
					return StateResult.getSlefSR(40002, "手机号已经存在");
				}
			}
		}
		if(oa.getEmail()!=null&&!oa.getEmail().equals(admin.getEmail())){
		for (int i = 0; i < le.size(); i++) {
			if(le.get(i).equals(admin.getEmail())){
				return StateResult.getSlefSR(40002, "email已经存在");
			}
		}
		}
		boolean um = adminService.updateAdmin(admin);
		return StateResult.getSR(um);
	}
	/**
	 * 广告主金钱增加（收广告主钱）
	 * @return
	 */
	@RequestMapping(value = "/money/advertise", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult moneyAdvertiseAdmin(
			@ModelAttribute WaterInformation waterInformation,
			HttpSession session)  {
		Admin b = adminService.loadAdmin(waterInformation.getAdminId());
		b.setRecharge(b.getRecharge()+waterInformation.getMoney());//充值存储
		double nowMoney = b.getMoney()+waterInformation.getMoney();//金钱相加
		b.setMoney(nowMoney);
		//boolean um = adminService.moneyAdmin(waterInformation.getAdminId(),nowMoney);
		boolean um = adminService.updateAdmin(b);
		if(um){
			waterInformationService.addWaterInformation(waterInformation);
		}
		return StateResult.getSR(um);
	}
	/**
	 * 渠道主金钱减少（打款给渠道主）
	 * @return
	 */
	@RequestMapping(value = "/money/advertiseSpace", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult moneyAdvertiseSpaceAdmin(
			@ModelAttribute WaterInformation waterInformation,
			HttpSession session)  {
		Admin b = adminService.loadAdmin(waterInformation.getAdminId());
		b.setWithdrawals(b.getWithdrawals()+waterInformation.getMoney());//提现存储
		double nowMoney = b.getMoney()-waterInformation.getMoney();//金钱相减
		b.setMoney(nowMoney);
		//boolean um = adminService.moneyAdmin(waterInformation.getAdminId(),nowMoney);
		boolean um = adminService.updateAdmin(b);
		if(um){
			waterInformationService.addWaterInformation(waterInformation);
		}
		return StateResult.getSR(um);
	}
	/**
	 * 修改密码
	 * @return
	 */
	@RequestMapping(value = "/update/password", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updatePasswordAdmin(@RequestParam(value="password")String password,@RequestParam(value="newpassword")String newpassword,HttpSession session)  {
		boolean um=false;
		if(session.getAttribute("admin")==null){
			return StateResult.getSR(um);
		}
		Admin admin = adminService.loadAdmin(((Admin) session.getAttribute("admin")).getAdminId());
			if(!admin.getPassword().equals(MyDESutil.getMD5(password))){
				return StateResult.getSR(um);
			}
			
			admin.setPassword(MyDESutil.getMD5(newpassword));
			um = adminService.updateAdmin(admin);
		return StateResult.getSR(um);
	}
	
	/**
	 * 邮箱/手机验证码发送
	 * 
	 * @param adminName
	 * @param session
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/validCode", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody
	StateResult validCode(@RequestParam("adminName") final String adminName,HttpSession session)  {
		//不能添加相同的手机号或者邮箱
				List<String> lp = adminService.browseAllAdminPhone();
				List<String> le = adminService.browseAllAdminEmail();
				lp.removeAll(Collections.singleton(null));
				le.removeAll(Collections.singleton(null));
					for (int i = 0; i < lp.size(); i++) {
						if(lp.get(i).equals(adminName)){
							return StateResult.getSlefSR(40002, "手机号已经存在");
						}
					}
				for (int i = 0; i < le.size(); i++) {
					if(le.get(i).equals(adminName)){
						return StateResult.getSlefSR(40002, "email已经存在");
					}
				}
		
		String uvc="";
		String uvce="";
		if(Pattern.matches(MyValidator.REGEX_EMAIL,adminName)){
			uvc="emailValidCode";
			uvce="emailValidCodeExpire";
		}
		if(Pattern.matches(MyValidator.REGEX_PHONE,adminName)){
			uvc="phoneValidCode";
			uvce="phoneValidCodeExpire";
		}
		
		if(session.getAttribute(uvce)!=null){
			String sessionvce = session.getAttribute(uvce).toString();
			try {
				if(!(new Date().after(DateUtil.getFirstToSecondsTime(DateUtil.parseDate(sessionvce), 1)))){//没超过一分钟
					return StateResult.getSlefSR(StatusCode.GetValueByKey(StatusCode.SUCCESS), StatusCode.GetValueByKey(StatusCode.ONE_ASK_ONE));
}
			} catch (ParseException e) {
				return StateResult.getFail();
			}
			
		}		
		Integer userValidCode=(int) (Math.random()*9000)+1000;
		try {
			if(Pattern.matches(MyValidator.REGEX_EMAIL,adminName)){
				SendMailDemo.sendSafeMail(adminName,Integer.valueOf(userValidCode));
			}
			if(Pattern.matches(MyValidator.REGEX_PHONE,adminName)){
				SMSInterface.SmsNumSend(String.valueOf(userValidCode), adminName,ThirdParty.GetValueByKey(ThirdParty.ALIBABA_SMS_SIGN_NAME),ThirdParty.GetValueByKey(ThirdParty.ALIBABA_SMS_TEMPLATE_CODE_ID));
			}
		} catch (NumberFormatException e) {
			return null;
		} catch (InterruptedException e) {
			return null;
		}
		session.setAttribute("adminName",adminName);
		session.setAttribute(uvc,userValidCode);
		session.setAttribute(uvce,DateUtil.getCurrentTime());
		return StateResult.getSuccess();
	}
	
	
	/**
	 * 注册
	 * @return
	 */
	@RequestMapping(value = "/register", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody  StateResult registerAdmin(
			@RequestParam(value="adminName")String adminName,
			@RequestParam(value="password")String password,
			@RequestParam("validCode") String validCode,
			@RequestParam(value="roleId")Integer roleId,HttpSession session)  {
		       
			if(!session.getAttribute("adminName").equals(adminName)){
				return StateResult.getFail();
			}
			if(!NumberUtil.isNumeric(validCode)){
				return StateResult.getFail();
			}
			if(session.getAttribute("phoneValidCodeExpire")==null && session.getAttribute("emailValidCodeExpire")==null){
				return StateResult.getSlefSR(40004, StatusCode.GetValueByKey("VERIFICATION_CODE_EXPIRED"));//验证码过期
			}
		       //不能添加相同的手机号或者邮箱
				List<String> lp = adminService.browseAllAdminPhone();
				List<String> le = adminService.browseAllAdminEmail();
				lp.removeAll(Collections.singleton(null));
				le.removeAll(Collections.singleton(null));
				for (int i = 0; i < lp.size(); i++) {
					if(lp.get(i).equals(adminName)){
						return StateResult.getSlefSR(40002, "手机已经存在");
					}
				}
				for (int i = 0; i < le.size(); i++) {
					if(le.get(i).equals(adminName)){
						return StateResult.getSlefSR(40002, "email已经存在");
					}
				}
				Admin admin =new Admin();
				String uvc="";
				String uvce="";
				if(Pattern.matches(MyValidator.REGEX_EMAIL,adminName)){
					uvc="emailValidCode";
					uvce="emailValidCodeExpire";
					admin.setEmail(adminName);
				}
				if(Pattern.matches(MyValidator.REGEX_PHONE,adminName)){
					uvc="phoneValidCode";
					uvce="phoneValidCodeExpire";
					admin.setCellPhone(adminName);
				}
				
				String sessionvce = session.getAttribute(uvce).toString();
				try {
					if(!(new Date().after(DateUtil.getFirstToSecondsTime(DateUtil.parseDate(sessionvce), 10)))){//10分钟没过期
						if(Integer.valueOf(session.getAttribute(uvc).toString()).equals(Integer.valueOf(validCode))){
						String shalp = MyDESutil.getMD5(password);
						admin.setPassword(shalp);
						admin.setRoleId(roleId);
						boolean am = adminService.addAdmin(admin);
					
					if(am){
						//成功则清除validcode
						session.removeAttribute(uvc);
						session.removeAttribute(uvce);
						session.removeAttribute("adminName");
						//session.setAttribute("admin", admin);
						//Role role = roleService.loadRole(roleId);
						//session.setAttribute("role", role);
						return StateResult.getSuccess();
					}
						}
						return StateResult.getSlefSR(40004, StatusCode.GetValueByKey("VERIFICATION_CODE_ERROR"));//验证码错误
					}
				} catch (NumberFormatException | ParseException e) {
					return StateResult.getFail();
				}
				return StateResult.getSlefSR(40005, StatusCode.GetValueByKey("VERIFICATION_CODE_EXPIRED"));//验证码过期
	}
	/**
	 * 管理员增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addAdmin(@ModelAttribute Admin admin, HttpSession session) {
		//不能添加相同的手机号或者邮箱
		List<String> lp = adminService.browseAllAdminPhone();
		List<String> le = adminService.browseAllAdminEmail();
		lp.removeAll(Collections.singleton(null));
		le.removeAll(Collections.singleton(null));
		for (int i = 0; i < lp.size(); i++) {
			if(lp.get(i).equals(admin.getCellPhone())){
				return StateResult.getSlefSR(40002, "手机号已经存在");
			}
		}
		for (int i = 0; i < le.size(); i++) {
			if(le.get(i).equals(admin.getEmail())){
				return StateResult.getSlefSR(40002, "email已经存在");
			}
		}
		admin.setPassword( MyDESutil.getMD5(admin.getPassword()));
		boolean am = adminService.addAdmin(admin);
		return StateResult.getSR(am);
	}
	/**
	 * 二级代理增加
	 * @return 
	 */
	@RequestMapping(value = "/increase", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult increaseAdmin(@ModelAttribute Admin admin, HttpSession session) {
		Admin sessionAdmin = (Admin) session.getAttribute("admin");
		if(sessionAdmin==null||!sessionAdmin.getAdminId().equals(admin.getParentId())){
			return StateResult.getSR(false);
		}
		//不能添加相同的手机号或者邮箱
		List<String> lp = adminService.browseAllAdminPhone();
		List<String> le = adminService.browseAllAdminEmail();
		lp.removeAll(Collections.singleton(null));
		le.removeAll(Collections.singleton(null));
		for (int i = 0; i < lp.size(); i++) {
			if(lp.get(i).equals(admin.getCellPhone())){
				return StateResult.getSlefSR(40002, "手机号已经存在");
			}
		}
		for (int i = 0; i < le.size(); i++) {
			if(le.get(i).equals(admin.getEmail())){
				return StateResult.getSlefSR(40002, "email已经存在");
			}
		}
		admin.setPassword( MyDESutil.getMD5(admin.getPassword()));
		boolean am = adminService.addAdmin(admin);
		return StateResult.getSR(am);
	}
	/**
	 *管理员 删除
	 * @return
	 */
	@RequestMapping(value = "/{adminId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delAdmin(@PathVariable("adminId") Integer adminId,HttpSession session)  {
		boolean dm = adminService.delAdmin(adminId);
		return StateResult.getSR(dm);
	}
	/**
	 * 管理员浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="roleId",required=false) Integer roleId,
			@RequestParam(value="parentId",required=false) Integer parentId,
			HttpSession session)  {
		int count = adminService.countAll(roleId,parentId);
		return count;
	}
	/**
	 * 管理员单个加载
	 * @return
	 */
	@RequestMapping(value = "/{adminId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Admin loadAdmin(@PathVariable("adminId") Integer adminId,HttpSession session)  {
		Admin admin=new Admin();
		admin = adminService.loadAdmin(adminId);
		return admin;
	}
	/**
	 *管理员登录
	 * @return
	 */
	
	@RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Object loginAdmin(@RequestParam(value="adminName")String adminName,@RequestParam(value="password")String password,HttpServletRequest request,HttpServletResponse response)  {
		Admin admin =new Admin();
		String md5pwd = MyDESutil.getMD5(password);
		admin= adminService.loginAdmin(adminName, md5pwd);
//		if(admin.getStatus().equals("审核中")){
//			return StateResult.getSlefSR(40010, "账户审核中");
//		}
		if(admin.getStatus().equals("锁定")){
			return StateResult.getSlefSR(40011, "账户已锁定");
		}
		if(admin!=null){
			 //生成一个token，保存用户登录状态
	        // tokenManager.createToken("XuDeOAadmin",admin.getAdminId(),request,response);
			request.getSession().setAttribute("admin", admin);
			Role role = roleService.loadRole(admin.getRoleId());
			request.getSession().setAttribute("role", role);
			
		}
		return admin;
	}

	/**
	 *管理员登出
	 * @return
	 */
	@RequestMapping(value = "/loginout", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody void loginoutAdmin(HttpServletRequest request,HttpServletResponse response)  {
		 //tokenManager.deleteToken("XuDeOAadmin",request,response);
		request.getSession().invalidate();
	}
	/**
	 *管理员状态
	 * @return
	 */
	@RequestMapping(value = "/islogin", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody boolean isloginAdmin(HttpServletRequest request,HttpServletResponse response)  {
		//if(tokenManager.checkToken("XuDeOAadmin", tokenManager.getToken("XuDeOAadmin", request), request,response)){
			if(request.getSession().getAttribute("admin")!=null){
			return true;
		}
		return false;
	}
	/**
	 *获取token
	 * @return
	 */
	@RequestMapping(value = "/token", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody TokenModel tokenAdmin(HttpServletRequest request,HttpServletResponse response)  {
		return tokenManager.getToken("XuDeOAadmin", request);
	}
	
}
