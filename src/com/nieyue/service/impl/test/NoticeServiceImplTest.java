package com.nieyue.service.impl.test;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.nieyue.bean.Advertise;
import com.nieyue.bean.Notice;
import com.nieyue.service.AdvertiseService;
import com.nieyue.service.NoticeService;
import com.nieyue.util.DateUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/spring-dao.xml","classpath:config/spring-service.xml"})
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
public class NoticeServiceImplTest {
	@Resource
	 NoticeService  noticeService;
	@Resource
	AdvertiseService  advertiseService;
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddNotice() {
		Notice p = new Notice();
		p.setType("dsfdsf");
		p.setUpdateDate(new Date());
		noticeService.addNotice(p);
	}

	@Test
	public void testDelNotice() {
		noticeService.delNotice(1000);
	}

	@Test
	public void testUpdateNotice() {
		Notice p = noticeService.loadNotice(1000);
		p.setUpdateDate(new Date());
		noticeService.updateNotice(p);
	}

	@Test
	public void testLoadNotice() {
		//Notice n = noticeService.loadNotice(1011);
		//System.out.println(n);
		Advertise advertise = advertiseService.browsePagingAdvertiseSpaceShowAdvertise(0.6,"投放中","PC端");
		System.out.println(advertise);
		
	}

	@Test
	public void testCountAll() {
		int n = noticeService.countAll();
		System.out.println(n);
	}
	@Test
	public void dddredis() {
//		BoundSetOperations<String, String> bso = stringRedisTemplate.boundSetOps("c1");
//		bso.expire(DateUtil.currentToEndTime(), TimeUnit.SECONDS);
//		bso.add("dsd");
//		bso.add("dsd2");
//		bso.add("dsd2");
		if(stringRedisTemplate.boundSetOps("c1").getOperations()!=null){
			System.out.println(stringRedisTemplate.boundSetOps("c1").getKey());
			System.out.println(stringRedisTemplate.boundSetOps("c1").members());
			System.out.println(stringRedisTemplate.boundSetOps("c1").getExpire());
			System.out.println(stringRedisTemplate.boundSetOps("c1").getType());
		}
		// stringRedisTemplate.boundValueOps("a1").set("测试", DateUtil.currentToEndTime(), TimeUnit.SECONDS);
//		if(stringRedisTemplate.boundValueOps("a1").get()!=null){
//			System.out.println(stringRedisTemplate.boundValueOps("a1").getKey());
//			System.out.println(stringRedisTemplate.boundValueOps("a1").getExpire());
//			System.out.println(stringRedisTemplate.boundValueOps("a1").get());
//		}
	}


	@Test
	public void testBrowsePagingNotice() {
		List<Notice> l = noticeService.browsePagingNotice(1, 10, "update_date", "desc");
		for (int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i).getType());
			
		}
	}

}
