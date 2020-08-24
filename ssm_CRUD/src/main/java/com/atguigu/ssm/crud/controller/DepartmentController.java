package com.atguigu.ssm.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.ssm.crud.domain.Department;
import com.atguigu.ssm.crud.domain.Msg;
import com.atguigu.ssm.crud.service.DepartmentService;


@Controller
@RequestMapping("/dept")
public class DepartmentController {
	
	@Autowired
	private DepartmentService  departmentService;
   
	@RequestMapping("/deptJson")
	//@ResponseBody��java��������ݰ���json��ʽ���͵�ǰ��
	public @ResponseBody Msg findAll() {		
		List<Department> list=departmentService.findAll();
//		Msg msg=new Msg();//����ԭ���ĵ�ֵַΪ@5sac60d6
		//���ػ����ĵ�ֵַ�Ѿ��ı�Ҫע��@7dac60b7
		
		return Msg.success().addDate("list", list);
		
	}
}
