package com.atguigu.ssm.crud.test;


import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.ssm.crud.dao.DepartmentMapper;
import com.atguigu.ssm.crud.dao.EmployeeMapper;
import com.atguigu.ssm.crud.domain.Department;
import com.atguigu.ssm.crud.domain.Employee;
/**
 * spring�ĵ�Ԫ���ԣ�
 * ����ע�⣺
 * ע��һ@ContextConfiguration:ָ�������ļ���λ��
 * ע���@RunWith������ָ��ִ�еĲ��Ի���ΪSpringJUnit4ClassRunner
 * ע����@Autowired:������ע��springȥע��������ʵ�֣�mapper��ʽ��������ʹ�õĴ����࿪����
 * @author ϣ
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class Spring4Junit {
	private ApplicationContext applicationContext;
	
	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Test//�������ǰ�Ĳ��Է��� ������Ҫ�Լ�ȥ����ioc������Ȼ��ȥ�õ����mapper����
	public void test01() {
	applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	EmployeeMapper emapper= applicationContext.getBean(EmployeeMapper.class);
	
	Employee  employee=emapper.selectByPrimaryKeyWithDept(1);
	System.out.println(employee+","+employee.getDepartment().getdName());
	}
	
	@Test//ʹ��spring�ĵ�Ԫ����
	public void test02() {
		
		//System.out.println(departmentMapper.selectByPrimaryKey(1).getdName());
		/*departmentMapper.insert(new Department(null, "������") );
		departmentMapper.insert(new Department(null, "��Ʋ�") );*/
		/*employeeMapper.insert(new Employee(null, "��С��", "1", "123456789@qq.com", 1));
		employeeMapper.insert(new Employee(null, "��Сϣ", "1", "123456789@qq.com", 2));*/
		
		//������������������sqlSession���������һ��һ��ִ���ر���
		long start =System.currentTimeMillis();
		/*for (int i = 0; i < 1000; i++) {//������63������cpu�����й�
			String u=UUID.randomUUID().toString();
			String uuid=u.substring(0, 5);
			employeeMapper.insert(new Employee(null,uuid,"1",uuid+"@qq.com",1));
			
		}*/
		
		
		//������������������sqlSession
		System.out.println(sqlSession);
		EmployeeMapper emapper= sqlSession.getMapper(EmployeeMapper.class);
		for (int i = 0; i < 10; i++) {//������55��
			String u=UUID.randomUUID().toString();
			String uuid=u.substring(0, 5);
			emapper.insert(new Employee(null,uuid,"1",uuid+"@qq.com",1));
		}	
		long end =System.currentTimeMillis();
		System.out.println("���β������ˣ�"+(end-start)+"����");
	}
	
	
}
