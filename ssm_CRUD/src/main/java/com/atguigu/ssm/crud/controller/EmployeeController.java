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
@RequestMapping("/emp")//���ø�·��
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	
	//@ResponseBody��ע���ǽ�bean��������ת��Ϊ json���ݵĸ�ʽ���ظ�ǰ̨ҳ�� @RequestBody��json���ݸ�ʽת��Ϊbean�����ʽ(���ڲ���ǰ��)
	@RequestMapping("/empJson")
	public @ResponseBody Msg getEmpWithJson(@RequestParam(value="pn", defaultValue="1")Integer pageNum) {
		
		//����pagehelper�����pom��������� ��mybatisȫ�������ļ���������
		PageHelper.startPage(pageNum, 5);//ֻҪ�������������list�ͻᱻ������ؽ��д���
		List<Employee> list = service.findAll();
		//PageInfo�Ĵ��� PageInfo�и�����ϸ�ķ�ҳ��Ϣ �������ǲ�ѯ��������Ҳ������ ��������������ʾ�ķ�ҳ��
		PageInfo<Employee> pageInfo =new PageInfo<>(list, 5);//һ����ʾ���ҳ��
		return Msg.success().addDate("pageInfo", pageInfo);	
	}
	
	
	
	/**
	 * ���շ�ҳ��ѯʱ������Ҫ�õ�ǰ�˵�ҳ������������Ҫ��������
	 * @RequestParamָ��ǰ�˴��ݹ����Ĳ��������������ǰ�˴���������һ����ô���ע��Ϳ��Բ���
	 * @param model
	 * @return
	 */
	//��ǰ��el���ʽ�ķ�ʽ
	@RequestMapping("/findAll")
	public String findAll(@RequestParam(value="pn", defaultValue="1")Integer pageNum , Model model) {
		//����pagehelper�����pom��������� ��mybatisȫ�������ļ���������
		PageHelper.startPage(pageNum, 5);//ֻҪ�������������list�ͻᱻ������ؽ��д���
		List<Employee> list = service.findAll();
		//PageInfo�Ĵ��� PageInfo�и�����ϸ�ķ�ҳ��Ϣ �������ǲ�ѯ��������Ҳ������ ��������������ʾ�ķ�ҳ��
		PageInfo<Employee> pageInfo =new PageInfo<>(list, 5);//һ����ʾ���ҳ��
		//��pageInfo���ݵ�ǰ̨���б�������
		model.addAttribute("pageInfo", pageInfo);
		return "main";
		
	}
	@RequestMapping(value="/addEmp",method=RequestMethod.POST)
	@ResponseBody   //BindingResult result���汣���˼�����Ϣ
	public Msg addEmp(@Valid Employee employee, BindingResult result) {
		//����ǰ��Ҫ����֤�����У��
		Map<String,Object> map =new HashMap<>();//�������������Ϣ
		if(result.hasErrors()) {	
			for (FieldError error :result.getFieldErrors()) {
				//error.getField()�����ֶ��� error.getDefaultMessage()������Ϣ
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
             return Msg.fail().addDate("regex", "���������ֻ���ĸ6~16λ,����2~5����!");
		}
		
		boolean b= service.checkUsername(empName);
		if(b) {
			return Msg.success();
			
		}else {
			 return Msg.fail().addDate("regex", "�û����Ѿ���ʹ��!");
		}
		
	}
	
	//ʹ��rest����ѯԱ����Ϣ
	@RequestMapping("/findEmp/{id}")
	public @ResponseBody Msg findEmp(@PathVariable("id") Integer id) {
		Employee emp = service.findEmp(id);
		
		return Msg.success().addDate("emp", emp);
		
	}
	/*��put����������һ��Ѫ��
	 * ����һ 1.��ҳ�淢��ajax����Ϊpost��ʱ��������޷�����������Ϊ��������˴��������Ϊput����
	 * �����data:$("#editEmpModal form").serialize()+"&_method=put" �����ֶ��ڴ������������_method=put�����������ͻᴦ��
	 * 2.����һ�������󣬻ᷢ�����Ǵ��ݹ�����������empIdΪnull�������ݶ��������ˣ��������Ǹ�������empIdΪ�����������������¾ͻ�ʧ��
	 * �����������Ҫ��rest��������{id}��Employee ��empId��������һ�������Ϳ��Դ��ݹ���Ȼ�����Ҳ�ܳɹ���
	 * 
	 * 
	 *�������1.���ǿ���ֱ�ӷ�������Ϊput��jaxa����data�����ֶ���ӵ�"&_method"��ɾ��������ͣ��ᱨ500����������յ�������ֻ��empIdΪʲô
	 *����Ӧ����ǰ�˴����װ��һ��Map�д��ݸ���˵�Ȼ������request.getParameter("xxx")����ȡ��
	 *�������ΪempId������ʹ��rest��񴫵ݹ����ģ�������������ȴû�д��ݹ�����ԭ���ǣ�
	 *Tomcatһ����put�������Ͳ����װ���ݣ�ֻ��post����Ż��װΪһ��Map
	 *������spring�ṩ��һ���������������tomcat������put�Ĵ���   ��
	 *HttpPutFormContentFilter�����Ϳ���ֱ�ӷ�put�����˶��ҿ��Եõ�����
	 *���������ǽ�put�����͹�����������������ݽ��з�װ����װ��һ��Map�����������ں�˿��Ի�ȡ
	 */
	
	//����Ա������
	@RequestMapping(value="/upadteEmp/{empId}",method=RequestMethod.PUT)
	public @ResponseBody Msg updateEmp(Employee employee) {
		System.out.println(employee);
		service.updateEmp(employee);
		return Msg.success();
		
	}
	//ɾ��Ա��  ��ɾ������ɾ�����ж�
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
