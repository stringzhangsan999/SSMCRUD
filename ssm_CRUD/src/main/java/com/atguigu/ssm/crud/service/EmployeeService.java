package com.atguigu.ssm.crud.service;

import java.util.List;

import com.atguigu.ssm.crud.domain.Employee;
import com.atguigu.ssm.crud.domain.Msg;

public interface EmployeeService {
	
	public List<Employee> findAll();
	public void addEmp(Employee employee);
	public boolean checkUsername(String empName);
	public Employee findEmp(Integer id);
	public int updateEmp(Employee employee);
	public void deleteEmp(Integer id);
	public void deleteBatch(List<Integer> list);
}
