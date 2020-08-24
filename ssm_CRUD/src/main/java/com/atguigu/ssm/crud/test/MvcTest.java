package com.atguigu.ssm.crud.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.atguigu.ssm.crud.domain.Employee;
import com.github.pagehelper.PageInfo;

/**
 * 使用spring提供的测试模块，里面有一个测试请求功能的测试模块，检测crud的正确性
 * 注意使用spring请求功能测试模块时，它是spring4所以我们需要使用servlet3.0所以注意检查servlet-api的版本
 * @author 希
 *
 */
@WebAppConfiguration//这个注解默认扫描的是"src/main/webapp"下的spring配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml","file:WebRoot/WEB-INF/springmvc-servlet.xml"})
public class MvcTest {
	@Autowired
	WebApplicationContext context  ;//按道理我们只能拿到ioc可以创建对象不能自己拿自己所以我们需要添加一个@WebAppConfiguration注解
	//虚拟的mvc
	MockMvc mockmvc;
	@Before
	public void initMockmvc() {
		mockmvc=MockMvcBuilders.webAppContextSetup(context).build();
	}
	@Test
	public void testMockmvc() throws Exception {
	MvcResult result =mockmvc.perform(MockMvcRequestBuilders.get("/emp/findAll.do").param("pn", "5")).andReturn();
	MockHttpServletRequest  request= result.getRequest();
	PageInfo<Employee> info = (PageInfo<Employee>) request.getAttribute("pageInfo");
		System.out.println("当前页码"+info.getPageNum());
		System.out.println("一页有多少条数据"+info.getPageSize());
		System.out.println("记录总数"+info.getTotal());
		System.out.println("一共有多少页"+info.getPages());
		
		//展示需要连续显示的页码
		int[] nums=info.getNavigatepageNums();
		for (int i : nums) {
			System.out.println(i);
		}
		
		//展示当前页数据
		List<Employee>  emps=info.getList();
		for (Employee employee : emps) {
			System.out.println(employee);
		}
	}
}
