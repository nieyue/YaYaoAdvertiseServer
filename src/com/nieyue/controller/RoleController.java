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

import com.nieyue.bean.Role;
import com.nieyue.exception.StateResult;
import com.nieyue.service.RoleService;


/**
 * 角色控制类
 * @author yy
 *
 */
@Controller("roleController")
@RequestMapping("/role")
public class RoleController {
	@Resource
	private RoleService roleService;
	
	/**
	 * 角色分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Role> browsePagingRole(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="role_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Role> list = new ArrayList<Role>();
			list= roleService.browsePagingRole(pageNum, pageSize, orderName, orderWay);
			return list;
	}
	/**
	 * 角色修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateRole(@ModelAttribute Role role,HttpSession session)  {
		boolean um = roleService.updateRole(role);
		return StateResult.getSR(um);
	}
	/**
	 * 角色增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addRole(@ModelAttribute Role role, HttpSession session) {
		boolean am = roleService.addRole(role);
		return StateResult.getSR(am);
	}
	/**
	 * 角色删除
	 * @return
	 */
	@RequestMapping(value = "/{roleId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delRole(@PathVariable("roleId") Integer roleId,HttpSession session)  {
		boolean dm = roleService.delRole(roleId);
		return StateResult.getSR(dm);
	}
	/**
	 * 角色浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = roleService.countAll();
		return count;
	}
	/**
	 * 角色单个加载
	 * @return
	 */
	@RequestMapping(value = "/{roleId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Role loadRole(@PathVariable("roleId") Integer roleId,HttpSession session)  {
		Role role=new Role();
		if(session.getAttribute("role")!=null){
			role=(Role) session.getAttribute("role");
		}else{
			role = roleService.loadRole(roleId);
			 session.setAttribute("role",role);
		}
		return role;
	}
	
}
