package com.nieyue.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nieyue.controller.NoticeController;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:config/spring-dao.xml","classpath:config/spring-service.xml","classpath:config/springmvc-servlet.xml"})
public class NoticeControllerTest {
	@Resource
	NoticeController noticeController;
	@Resource
	WebApplicationContext wac;
	MockHttpServletRequest request=new MockHttpServletRequest();
	MockHttpServletResponse response=new MockHttpServletResponse();
	MockMvc mvc;
	@Before
	public void setUp() throws Exception {
		this.mvc=MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBrowseMer() throws Exception {
		String url="/notice/list.xml?orderName=update_date&orderWay=desc&pageNum=1&pageSize=3";
		//String url="/mer/list?pageNum=2&pageSize=3";
		//String url="/mer/list.xml";
		this.mvc.perform(get(url))
		//.andExpect(status().isOk())
		.andDo(print());
	}
	@Test
	public void testDelMer() throws Exception {
		String url="/mer/1033/delete";
		this.mvc.perform(get(url))
		//.andExpect(status().isOk())
		.andDo(print());
	}
	@Test
	public void testaddMer() throws Exception {
		String url="/mer/add?merTitle=asdf";
		this.mvc.perform(get(url))
		//.andExpect(status().isOk())
		.andDo(print());
	} 
	@Test
	public void testupdateMer() throws Exception {
		String url="/mer/update?merId=1033&merPostage=0&merDiscount=1&merStatus=0&merUpdateTime=2011-11-11 20:22:22";
		//String url="/exception/sellerSession";
		//String url="/exception/a";
		this.mvc.perform(get(url))
		//.andExpect(status().isOk())
		.andDo(print());
	}
	@Test
	public void testCount() throws Exception {
		//String url="/admin/count?roleId=1000";
		//String url="/admin/count";
		String url="/admin/count?roleId=df";
		this.mvc.perform(get(url))
		//.andExpect(status().isOk())
		.andDo(print());
	}
	@Test
	public void testLoadMer() throws Exception {
		//String url="/mer/1026";
		//String url="/admin/login?adminName=15111336587&password=123456";
		String url="/advertise/advertiseSpaceShowAdvertise?advertiseSpaceId=1009";
		//String url="/admin/1000";
		this.mvc.perform(get(url))
		//.andExpect(status().isOk())
		.andDo(print());
	}

}
