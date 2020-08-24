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
	//@ResponseBody将java对象的数据按照json格式发送到前端
	public @ResponseBody Msg findAll() {		
		List<Department> list=departmentService.findAll();
//		Msg msg=new Msg();//例如原来的地址值为@5sac60d6
		//返回回来的地址值已经改变要注意@7dac60b7
		
		return Msg.success().addDate("list", list);
		
	}
}
