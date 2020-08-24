package com.atguigu.ssm.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.ssm.crud.dao.EmployeeMapper;
import com.atguigu.ssm.crud.domain.Employee;
import com.atguigu.ssm.crud.domain.EmployeeExample;
import com.atguigu.ssm.crud.domain.EmployeeExample.Criteria;
import com.atguigu.ssm.crud.domain.Msg;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeMapper mapper;
	@Override
	public List<Employee> findAll() {
		
		return mapper.selectByExampleWithDept(null);
	}
	
	@Override
	public void addEmp(Employee employee) {
		mapper.insertSelective(employee);
		
	}

	@Override
	public boolean checkUsername(String empName) {
		EmployeeExample example =new EmployeeExample();
		//创建criteria拼装条件
		Criteria criteria= example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		
		int count=mapper.countByExample(example);
		
		return count==0;

	}

	@Override
	public Employee findEmp(Integer id) {
		
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateEmp(Employee employee) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(employee);
	}

	@Override
	public void deleteEmp(Integer id) {
		
		mapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public void deleteBatch(List<Integer> list) {
		//System.out.println(list);
		EmployeeExample example =new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpIdIn(list);
		mapper.deleteByExample(example);
		
	}

}
