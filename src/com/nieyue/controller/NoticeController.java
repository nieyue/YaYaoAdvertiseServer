package com.nieyue.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
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

import com.nieyue.bean.Notice;
import com.nieyue.exception.StateResult;
import com.nieyue.service.NoticeService;


/**
 * 公告控制类
 * @author yy
 *
 */
@Controller("noticeController")
@RequestMapping("/notice")
public class NoticeController {
	@Resource
	private NoticeService noticeService;
	
	/**
	 * 公告分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Notice> browsePagingNotice(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="notice_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Notice> list = new ArrayList<Notice>();
			list= noticeService.browsePagingNotice(pageNum, pageSize, orderName, orderWay);
			return list;
	}
	/**
	 * 公告修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateNotice(@ModelAttribute Notice notice,HttpSession session)  {
		boolean um = noticeService.updateNotice(notice);
		return StateResult.getSR(um);
	}
	/**
	 * 公告增加
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addNotice(@ModelAttribute Notice notice, HttpSession session) {
		boolean am = noticeService.addNotice(notice);
		return StateResult.getSR(am);
	}
	/**
	 * 公告删除
	 * @return
	 */
	@RequestMapping(value = "/{noticeId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delNotice(@PathVariable("noticeId") Integer noticeId,HttpSession session)  {
		boolean dm = noticeService.delNotice(noticeId);
		return StateResult.getSR(dm);
	}
	/**
	 * 公告浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = noticeService.countAll();
		return count;
	}
	/**
	 * 公告单个加载
	 * @return
	 */
	@RequestMapping(value = "/{noticeId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Notice loadNotice(@PathVariable("noticeId") Integer noticeId,HttpSession session)  {
		Notice notice=new Notice();
		notice = noticeService.loadNotice(noticeId);
		return notice;
	}
	
}
