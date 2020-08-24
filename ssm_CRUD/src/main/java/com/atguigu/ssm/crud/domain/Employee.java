package com.atguigu.ssm.crud.domain;

import javax.validation.constraints.Pattern;

public class Employee {
 
	private Integer empId;
	//这个是JSR303标准的校验方式  注意\在Java中是转义字符我们需要变为\\ 但这个地方\u2E80为Unicode编码所以不用\\
	@Pattern(regexp="(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})",message="请输入数字或字母6~16位,或者2~5中文!(后)")
    private String empName;

    private String gender;
    @Pattern(regexp="^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$",message="请输入正确的邮箱格式!(后)")
    private String email;

    private Integer dId;

    //我们想要查询员工时也把部门信息给查询出来 分析：一个员工对应一个部门所以是一对一 这里注意主体是员工（一个部门对应多个员工【除非主体是部门那么就是一对多】）
    private Department department;
    
    
    
    public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

    
	public Employee(Integer empId, String empName, String gender, String email, Integer dId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.gender = gender;
		this.email = email;
		this.dId = dId;
	}


	public Department getDepartment(){
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }
    @Override
 	public String toString() {
 		return "Employee [empId=" + empId + ", empName=" + empName + ", gender=" + gender + ", email=" + email
 				+ ", dId=" + dId + ", department=" + department + "]";
 	}
}