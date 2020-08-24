package com.atguigu.ssm.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.ssm.crud.domain.Employee;
import com.atguigu.ssm.crud.domain.Msg;
import com.atguigu.ssm.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/emp")//设置根路径
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	
	//@ResponseBody此注解是将bean对象数据转换为 json数据的格式返回给前台页面 @RequestBody将json数据格式转换为bean对象格式(放在参数前面)
	@RequestMapping("/empJson")
	public @ResponseBody Msg getEmpWithJson(@RequestParam(value="pn", defaultValue="1")Integer pageNum) {
		
		//引入pagehelper插件在pom中添加依赖 在mybatis全局配置文件中引入插件
		PageHelper.startPage(pageNum, 5);//只要调用了这个方法list就会被插件拦截进行代理
		List<Employee> list = service.findAll();
		//PageInfo的创建 PageInfo有更加详细的分页信息 包括我们查询到的数据也在里面 ，还可以设置显示的分页码
		PageInfo<Employee> pageInfo =new PageInfo<>(list, 5);//一次显示五个页码
		return Msg.success().addDate("pageInfo", pageInfo);	
	}
	
	
	
	/**
	 * 按照分页查询时我们需要得到前端的页码所以我们需要两个参数
	 * @RequestParam指定前端传递过来的参数如果参数名和前端传来的名称一致那么这个注解就可以不用
	 * @param model
	 * @return
	 */
	//用前端el表达式的方式
	@RequestMapping("/findAll")
	public String findAll(@RequestParam(value="pn", defaultValue="1")Integer pageNum , Model model) {
		//引入pagehelper插件在pom中添加依赖 在mybatis全局配置文件中引入插件
		PageHelper.startPage(pageNum, 5);//只要调用了这个方法list就会被插件拦截进行代理
		List<Employee> list = service.findAll();
		//PageInfo的创建 PageInfo有更加详细的分页信息 包括我们查询到的数据也在里面 ，还可以设置显示的分页码
		PageInfo<Employee> pageInfo =new PageInfo<>(list, 5);//一次显示五个页码
		//将pageInfo传递到前台进行遍历即可
		model.addAttribute("pageInfo", pageInfo);
		return "main";
		
	}
	@RequestMapping(value="/addEmp",method=RequestMethod.POST)
	@ResponseBody   //BindingResult result里面保存了检验信息
	public Msg addEmp(@Valid Employee employee, BindingResult result) {
		//保存前需要先验证，后端校验
		Map<String,Object> map =new HashMap<>();//用来保存错误信息
		if(result.hasErrors()) {	
			for (FieldError error :result.getFieldErrors()) {
				//error.getField()错误字段名 error.getDefaultMessage()错误信息
				map.put(error.getField(), error.getDefaultMessage());
			}
			return Msg.fail().addDate("errors", map);
		}
        service.addEmp(employee);
        
		return Msg.success();
		
	}
	@RequestMapping("/checkUserName")
	@ResponseBody
	public Msg checkUserName(String empName) {
		String regex="(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		boolean c= empName.matches(regex);
		if(!c) {
             return Msg.fail().addDate("regex", "请输入数字或字母6~16位,或者2~5中文!");
		}
		
		boolean b= service.checkUsername(empName);
		if(b) {
			return Msg.success();
			
		}else {
			 return Msg.fail().addDate("regex", "用户名已经被使用!");
		}
		
	}
	
	//使用rest风格查询员工信息
	@RequestMapping("/findEmp/{id}")
	public @ResponseBody Msg findEmp(@PathVariable("id") Integer id) {
		Employee emp = service.findEmp(id);
		
		return Msg.success().addDate("emp", emp);
		
	}
	/*由put请求引发的一场血案
	 * 问题一 1.当页面发送ajax类型为post的时候服务器无法正常处理因为服务器这端处理的请求为put请求
	 * 解决：data:$("#editEmpModal form").serialize()+"&_method=put" 我们手动在传递数据中添加_method=put这样服务器就会处理
	 * 2.问题一问题解决后，会发现我们传递过来的数据中empId为null其他数据都传过来了，但是我们更新是由empId为条件，所以这样更新就会失败
	 * 解决：我们需要将rest风格里面的{id}与Employee 中empId属性名称一致这样就可以传递过来然后更新也能成功！
	 * 
	 * 
	 *问题二：1.我们可以直接发送类型为put的jaxa请求将data后面手动添加的"&_method"给删掉点击发送，会报500服务器这边收到的数据只有empId为什么
	 *数据应该由前端打包封装在一个Map中传递给后端的然后后端用request.getParameter("xxx")来获取的
	 *解决：因为empId是我们使用rest风格传递过来的，而其他数据是却没有传递过来，原因是：
	 *Tomcat一看是put请求，它就不会封装数据，只有post请求才会封装为一个Map
	 *而我们spring提供了一个过滤器来规避了tomcat不处理put的错误   ：
	 *HttpPutFormContentFilter这样就可以直接发put请求了而且可以得到数据
	 *它的作用是将put请求发送过来的请求体里的数据进行封装，封装到一个Map中这样我们在后端可以获取
	 */
	
	//更新员工数据
	@RequestMapping(value="/upadteEmp/{empId}",method=RequestMethod.PUT)
	public @ResponseBody Msg updateEmp(Employee employee) {
		System.out.println(employee);
		service.updateEmp(employee);
		return Msg.success();
		
	}
	//删除员工  单删和批量删进行判断
	@RequestMapping(value="/deleteEmp/{ids}",method=RequestMethod.DELETE)
	public @ResponseBody Msg deleteEmp(@PathVariable("ids") String ids) {
		//System.out.println(id);
		if(ids.contains("-")) {
		 String[] emp_ids =ids.split("-");
		 List<Integer> list =new ArrayList<>();
		 for (String string : emp_ids) {
			 list.add(Integer.parseInt(string));
		}
		 
		 service.deleteBatch(list);
			
		}else {
	
		service.deleteEmp(Integer.parseInt(ids));
		}
		return Msg.success();
		
	}

}
