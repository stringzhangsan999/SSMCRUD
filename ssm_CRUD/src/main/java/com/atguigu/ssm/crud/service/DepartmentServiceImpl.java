package com.atguigu.ssm.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.ssm.crud.dao.DepartmentMapper;
import com.atguigu.ssm.crud.domain.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentMapper mapper;
	@Override
	public List<Department> findAll() {
		return mapper.selectByExample(null);
	}

}
