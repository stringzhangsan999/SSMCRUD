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
 * spring的单元测试：
 * 三个注解：
 * 注解一@ContextConfiguration:指定配置文件的位置
 * 注解二@RunWith：用来指定执行的测试环境为SpringJUnit4ClassRunner
 * 注解三@Autowired:按类型注入spring去注入代理类的实现（mapper方式开发就是使用的代理类开发）
 * @author 希
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
	
	@Test//这个是以前的测试方法 我们需要自己去创建ioc容器，然后去得到这个mapper对象
	public void test01() {
	applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	EmployeeMapper emapper= applicationContext.getBean(EmployeeMapper.class);
	
	Employee  employee=emapper.selectByPrimaryKeyWithDept(1);
	System.out.println(employee+","+employee.getDepartment().getdName());
	}
	
	@Test//使用spring的单元测试
	public void test02() {
		
		//System.out.println(departmentMapper.selectByPrimaryKey(1).getdName());
		/*departmentMapper.insert(new Department(null, "开发部") );
		departmentMapper.insert(new Department(null, "设计部") );*/
		/*employeeMapper.insert(new Employee(null, "尹小炮", "1", "123456789@qq.com", 1));
		employeeMapper.insert(new Employee(null, "张小希", "1", "123456789@qq.com", 2));*/
		
		//测试批量处理不用批量sqlSession这样语句是一条一条执行特别慢
		long start =System.currentTimeMillis();
		/*for (int i = 0; i < 1000; i++) {//花费了63秒这与cpu性能有关
			String u=UUID.randomUUID().toString();
			String uuid=u.substring(0, 5);
			employeeMapper.insert(new Employee(null,uuid,"1",uuid+"@qq.com",1));
			
		}*/
		
		
		//测试批量处理用批量sqlSession
		System.out.println(sqlSession);
		EmployeeMapper emapper= sqlSession.getMapper(EmployeeMapper.class);
		for (int i = 0; i < 10; i++) {//花费了55秒
			String u=UUID.randomUUID().toString();
			String uuid=u.substring(0, 5);
			emapper.insert(new Employee(null,uuid,"1",uuid+"@qq.com",1));
		}	
		long end =System.currentTimeMillis();
		System.out.println("本次操作花了："+(end-start)+"毫秒");
	}
	
	
}
