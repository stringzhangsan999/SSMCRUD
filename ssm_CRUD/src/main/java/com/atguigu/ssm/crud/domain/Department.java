package com.atguigu.ssm.crud.domain;

public class Department {
    private Integer dId;

    private String dName;


	

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Department(Integer dId, String dName) {
		super();
		this.dId = dId;
		this.dName = dName;
	}


	public Integer getdId() {
		return dId;
	}

	public void setdId(Integer dId) {
		this.dId = dId;
	}

	public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName == null ? null : dName.trim();
    }

	@Override
	public String toString() {
		return "Department [dId=" + dId + ", dName=" + dName + "]";
	}
    
}