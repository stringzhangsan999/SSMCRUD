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
    //我们想要查询员工时带有部门信息所以需要做关联查询
    Employee selectByPrimaryKeyWithDept(Integer empId);
    //我们想要查询员工时带有部门信息所以需要做关联查询
    List<Employee> selectByExampleWithDept(EmployeeExample example);
}