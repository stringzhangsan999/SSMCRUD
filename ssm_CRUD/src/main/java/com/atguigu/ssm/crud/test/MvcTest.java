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
 * ʹ��spring�ṩ�Ĳ���ģ�飬������һ�����������ܵĲ���ģ�飬���crud����ȷ��
 * ע��ʹ��spring�����ܲ���ģ��ʱ������spring4����������Ҫʹ��servlet3.0����ע����servlet-api�İ汾
 * @author ϣ
 *
 */
@WebAppConfiguration//���ע��Ĭ��ɨ�����"src/main/webapp"�µ�spring�����ļ�
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml","file:WebRoot/WEB-INF/springmvc-servlet.xml"})
public class MvcTest {
	@Autowired
	WebApplicationContext context  ;//����������ֻ���õ�ioc���Դ����������Լ����Լ�����������Ҫ���һ��@WebAppConfigurationע��
	//�����mvc
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
		System.out.println("��ǰҳ��"+info.getPageNum());
		System.out.println("һҳ�ж���������"+info.getPageSize());
		System.out.println("��¼����"+info.getTotal());
		System.out.println("һ���ж���ҳ"+info.getPages());
		
		//չʾ��Ҫ������ʾ��ҳ��
		int[] nums=info.getNavigatepageNums();
		for (int i : nums) {
			System.out.println(i);
		}
		
		//չʾ��ǰҳ����
		List<Employee>  emps=info.getList();
		for (Employee employee : emps) {
			System.out.println(employee);
		}
	}
}
