package com.atguigu.ssm.crud.dao;

import com.atguigu.ssm.crud.domain.Employee;
import com.atguigu.ssm.crud.domain.EmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    int countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer empId);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
    //������Ҫ��ѯԱ��ʱ���в�����Ϣ������Ҫ��������ѯ
    Employee selectByPrimaryKeyWithDept(Integer empId);
    //������Ҫ��ѯԱ��ʱ���в�����Ϣ������Ҫ��������ѯ
    List<Employee> selectByExampleWithDept(EmployeeExample example);
}