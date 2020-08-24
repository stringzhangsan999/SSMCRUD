<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BootStrap</title>
<%pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<!-- web路径
分两种：1.不以/开始的相对路径，找资源，以当前资源路径为基准，但是这种方法经常出问题所以我们推荐第二种
2.(推荐)以/开始的相对路径,找资源，以服务器的路径为基准(http://localhost:8080)所以需要加上项目名
 也就是/项目名/.../资源路径
 ${pageContext.request.contextPath }==/ssm_CRUD
 -->
<!-- 引入jquey -->
<script type="text/javascript" src="${APP_PATH }/js/jquery-1.12.4.min.js"></script>
<!-- 引入BootStrap 的css  rel="stylesheet"这个属性必须有不然不能算是外部引用-->
 <link rel="stylesheet" href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" >
<!-- 引入BootStrap的js -->
<script type="text/javascript" src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>
<body>
<!-- 修改员工的model -->
<div class="modal fade" id="editEmpModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">员工修改</h4>
      </div>
      <div class="modal-body">
      <!-- 添加输入框 -->
        <form class="form-horizontal">
			  <div class="form-group" id="emp_name_edit">
			    <label for="empName3_update" class="col-sm-2 control-label">员工姓名:</label>
			    <div class="col-sm-10" id="111">
			    <p class="form-control-static" id="empName3_update" name="empName"></p>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="gender_update" class="col-sm-2 control-label">性别:</label>
			    <div class="col-sm-10">
			        <label class="radio-inline">
					  <input type="radio" name="gender" id="gender_update" value="1">男
					</label>
					<label class="radio-inline">
					  <input type="radio" name="gender" id="gender_update" value="0">女
					</label>
			    </div>
			  </div>
			   <div class="form-group" id="emp_email_update">
			    <label for="email3_update" class="col-sm-2 control-label">邮箱:</label>
			    <div class="col-sm-10">
			      <input type="email" class="form-control" id="email3_update" placeholder="邮箱" name="email">
			   <span  class="help-block"></span>
			    </div>
			  </div>
				   <div class="form-group">
			    <label for="department3_update" class="col-sm-2 control-label">部门:</label>
			    <div class="col-sm-3">
			      <select  class="form-control" id="department3_update"  name="dId">
			      
			      </select>
			    </div>
			  </div>
			
		</form>
        
        <!--  -->
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="edit_emp">更新</button>
      </div>
    </div>
  </div>
</div>
<!-- 添加员工的model -->
<div class="modal fade" id="addEmpModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">员工添加</h4>
      </div>
      <div class="modal-body">
      <!-- 添加输入框 -->
        <form class="form-horizontal">
			  <div class="form-group" id="emp_name">
			    <label for="inputEmpName3" class="col-sm-2 control-label">员工姓名:</label>
			    <div class="col-sm-10" id="111">
			      <input type="text"  class="form-control" id="inputEmpName3" placeholder="员工姓名" name="empName">
			     <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputGender3" class="col-sm-2 control-label">性别:</label>
			    <div class="col-sm-10">
			        <label class="radio-inline">
					  <input type="radio" name="gender" id="inputGender3" value="1">男
					</label>
					<label class="radio-inline">
					  <input type="radio" name="gender" id="inputGender3" value="0">女
					</label>
			    </div>
			  </div>
			   <div class="form-group" id="emp_email">
			    <label for="inputEmail3" class="col-sm-2 control-label">邮箱:</label>
			    <div class="col-sm-10">
			      <input type="email" class="form-control" id="inputEmail3" placeholder="邮箱" name="email">
			   <span  class="help-block"></span>
			    </div>
			  </div>
				   <div class="form-group">
			    <label for="inputDepartment3" class="col-sm-2 control-label">部门:</label>
			    <div class="col-sm-3">
			      <select  class="form-control" id="inputDepartment3"  name="dId">
			      
			      </select>
			    </div>
			  </div>
			
		</form>
        
        <!--  -->
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="save_emp">保存</button>
      </div>
    </div>
  </div>
</div>
<!-- 表格数据 -->
   <div class="container">
   <!-- 标题行 -->
   <div class="row">
    <div class="col-md-12">  
  <h1> SSM-员工信息</h1>
    </div>
   </div>
   <!-- 按钮行 -->
   <div class="row">
   <div class="col-md-4 col-md-offset-8">
   <button class="btn btn-primary" id="add_emp">新增</button>
   <button class="btn btn-danger" id="delete_batch">删除</button>
   </div>
   </div>
   <!-- 显示表格数据 -->
   <div class="row"> 
    <div class="col-md-12">  
   <table class="table table-hover" id="emps_table">
   <thead>
   <tr>
   <th><input type="checkbox" id="checkbox_all"></th>
   <th>员工编号</th>
   <th>员工姓名</th>
   <th>员工性别</th>
   <th>员工邮箱</th>
   <th>所处部门</th>
   <th>操作</th>
   </tr>
   </thead>
   <tbody>
   </tbody>
   </table>
    </div>
   </div>
   <!-- 分页 -->
   <div class="row" >
  <!-- 分页行信息 -->
  <div class="col-md-6" id="page_info">
  
  </div>
   
  <!-- 分页条 -->
   <div class="col-md-6" id="page_row">
 
   </div>
   </div>
   </div>
</body>
<script type="text/javascript">
var page_Info,currentPageNum;
$(function(){
	//加载页面默认去首页//服务器端默认就是1
	ajax_toPage(1);
	//注册一个可点添加员工的弹窗
	optionAddEmp();

	
	})	
	
	//发送ajax去指定页面
	function ajax_toPage(pageNum){	
	$.ajax({
		type: "GET",
		url: "${APP_PATH}/emp/empJson.do",
	    data: "pn="+pageNum,
		success: function(data){			
			/* console.log(data.extand.pageInfo.list[0]);
		     alert(data.extand.pageInfo.list[0].empName); */
			getEmployees(data);
		    getPageInfo(data);
		    getPageRow(data);
		   }	
	});
		
	}
	//解析员工表格信息
	function getEmployees(data){
		//"#emps_table tbody"
		//构建表格数据之前需要清空表格
		$("#emps_table tbody").empty();
		var emps =data.extand.pageInfo.list
		$.each(emps,function(index,item){			
			//alert(item.empName);
			var checkbox_id=$("<td><input type='checkbox' class='checkbox_itme' id="+item.empId+"/></td>");
			var empIdTd = $("<td></td>").append(item.empId);
			var empNameTd = $("<td></td>").append(item.empName);
			var genderTd = $("<td></td>").append(item.gender==1?"男":"女");
			var emailTd = $("<td></td>").append(item.email);
			var departmentTd = $("<td></td>").append(item.department.dName);
			
			var editBtn =$("<button></button>").addClass("btn btn-info btn-sm edit-btn").append("<span></span>").addClass("glyphicon glyphicon-pencil").append("编辑");
			editBtn.attr("edit-id",item.empId);
			
			var deleteBtn =$("<button></button>").addClass("btn btn-danger btn-sm del-btn").append("<span></span>").addClass("glyphicon glyphicon-trash").append("删除");
			deleteBtn.attr("del-id",item.empId)
			
			var btns =$("<td></td>").append(editBtn).append(" ").append(deleteBtn);

			
			$("<tr></tr>").append(checkbox_id)
			.append(empIdTd)
			.append(empNameTd)
			.append(genderTd)
			.append(emailTd)
			.append(departmentTd)
			.append(btns)
			.appendTo("#emps_table tbody");
		})
	}
	//解析 分页行信息
	function getPageInfo(data){
		//设置全局变量pages方便用于跳转到指定页面的值
		page_Info=data.extand.pageInfo;
		//#page_info
		//构建分页行信息之前先清空分页行信息
		$("#page_info").empty();
		var pageInfo = data.extand.pageInfo
		currentPageNum=pageInfo.pageNum;
		$("#page_info").append(" 当前 第："+pageInfo.pageNum +" 页 &emsp; 有"+pageInfo.pages +"页  &emsp; 一共有："+pageInfo.total+"条记录")
	}
	
	//解析分页条
	function getPageRow(data){	
		var pageInfo=data.extand.pageInfo;
	//	
	//构建分页条之前先清空分页条
	$("#page_row").empty();
	var firstPage = $("<li></li>").append($("<a></a>").attr("href","#").append("首页"));
   
	var lastPage = $("<li></li>").append($("<a></a>").attr("href","#").append("尾页"));
		
	var pre_page=$("<li></li>").append($("<a></a>").attr("href","#").append($("<span></span>").append("&laquo;")));
	var next_page=$("<li></li>").append($("<a></a>").attr("href","#").append($("<span></span>").append("&raquo;")));
	
	 if(!pageInfo.hasPreviousPage){
	    	firstPage.addClass("disabled");
	    	pre_page.addClass("disabled");
	    }else{
	    	firstPage.click(function(){
	    		ajax_toPage(1);
	    	})
	    	pre_page.click(function(){
	    		ajax_toPage(pageInfo.pageNum-1);
	    	})
	    }
	 if(!pageInfo.hasNextPage){
		  lastPage.addClass("disabled");
		  next_page.addClass("disabled");
	    }else{
	    	lastPage.click(function(){
	    		ajax_toPage(pageInfo.pages);
	    	})
	    	next_page.click(function(){
	    		ajax_toPage(pageInfo.pageNum+1);
	    	})
	    }
	
	var ul=$("<ul>").addClass("pagination").append(firstPage).append(pre_page);
	
	var li_list=null;
	$.each(pageInfo.navigatepageNums,function(index,item){
		li_list=$("<li>").append($("<a>").attr("href","#").append(item));
		if(pageInfo.pageNum==item){
			li_list.addClass("active");
		}
		li_list.click(function(){
			ajax_toPage(item)
		})
		ul.append(li_list);	
	})
	ul.append(next_page).append(lastPage);
	var nav=$("<nav>").attr("aria-label","Page navigation").append(ul).appendTo("#page_row")
	
	}
	
	//重置表单数据和状态
	function resetForm(ele){
		$(ele+" form")[0].reset();
		$(ele+" div").removeClass("has-success has-error");
		$(ele+" span").text("");
	}
	
	//新增弹窗
	function optionAddEmp(){
		
		$("#add_emp").click(function(){
			//添加数据应该将上一次添加信息给清空掉
			resetForm("#addEmpModal")
	     //当点击新增按钮时我们应该发送ajax给后端拿回部门信息
	     getDepartments("#addEmpModal");
     	$("#addEmpModal").modal({
    	 backdrop:"static"
     		});
	})
	}
	/*
	单纯的用click绑定不上！
	原因：js是先将页面加载,将所有函数和时间先加载，然后执行的ajax然后导致按钮事件绑定在之前执行，然而绑定时类选择器找不到类所以绑定不上
	解决：1.使用live给所有匹配的元素附加一个事件处理函数，即使这个元素是以后再添加进来的也有效。但是新版jequey没有live函数
	2.on在选择元素上绑定一个或多个事件的事件处理函数。但是还是处理不了后加载问题，所以我们使用document对象去绑定指定类，document对象会将加载好的页面进行文档解析所以很好规避这个问题
	*/
	//修改弹窗
	$(document).on("click",".edit-btn",function(){
		
		//添加数据应该将上一次添加信息给清空掉
		resetForm("#editEmpModal")
     //当点击新增按钮时我们应该发送ajax给后端拿回部门信息
     getDepartments("#editEmpModal");
		//查询员工信息
		getEmp($(this).attr("edit-id"))
		//给更新按钮附上edit-id属性
		$("#edit_emp").attr("edit-id",$(this).attr("edit-id"));
 	  $("#editEmpModal").modal({
	 backdrop:"static"
 		});
 	
	})
	//点击删除按钮提示是否删除,和他添加按钮一样这个需要在文档加载完后绑定一个事件
	$(document).on("click",".del-btn",function(){
		//1.点击删除弹出提示框是否删除
		if(confirm("是否确认删除"+$(this).parents("tr").find("td:eq(2)").text())){
		//2.如果点击确定的话就发送ajax
		$.ajax({
			type:"DELETE",
			url:"${APP_PATH}/rest/emp/deleteEmp/"+$(this).attr("del-id"),
			success:function(data){
				//提示成功信息
		    	confirm(data.msg)	
				//跳到删除的页面
				ajax_toPage(currentPageNum);
			}		
		})			
			return;
		}
		
	})
	
	
	function getEmp(id){
		$.ajax({
			url:"${APP_PATH}/rest/emp/findEmp/"+id,
			type:"GET",
			success:function(data){
				//console.log(data);
				//empName3_update
				$("#empName3_update").text(data.extand.emp.empName);
				//email3_update
				$("#email3_update").val(data.extand.emp.email);
				$("#editEmpModal input[name=gender]").val([data.extand.emp.gender])
				$("#editEmpModal select").val([data.extand.emp.dId])
			}
			
		})
		
	}
	
	//在select标签中添加option标签和其他属性值
	function addOption(data,ele){
		$(ele+" select").empty();
		var depts=data.extand.list;
		var options=null
		$.each(depts,function(){
			// 参数分为 索引和条目(也就是遍历的东西)  使用参数也行，使用this.属性名也行
		options=$("<option>").attr("value",this.dId).append(this.dName);
		options.appendTo(ele+" select")
		})
		

	}
	
	//获取部门名称
	function  getDepartments(ele){
		$.ajax({
			type:"GET",
			url:"${APP_PATH}/dept/deptJson.do",
			success:function(data){
				addOption(data,ele);
			}
		})
	}
	//发送ajax检验是否用户名重名
	$("#inputEmpName3").change(function(){
		var empName=this.value;
		$.ajax({
			type:"POST",
			url:"${APP_PATH}/emp/checkUserName.do",
			data:"empName="+empName,
			success:function(data){
				if(data.code==200){
					validate_state("#inputEmpName3","success",data.extand.regex)
					$("#save_emp").attr("ajax-va","success");
				}else{
					validate_state("#inputEmpName3","error",data.extand.regex)
					$("#save_emp").attr("ajax-va","error");
				}
				
			}
			
		})
	})
	
	
	
	
	function validate_state(ele,state,msg){
		$(ele).parent().removeClass("has-success has-error")
		$(ele).next("span").empty();
		if("success"==state){
			$(ele).parent().addClass("has-success")
		}else if("error"==state){
			$(ele).parent().addClass("has-error")
			$(ele).next("span").append(msg)
		}
		
	}
	
	//校验功能
	function validate(){
		var empname=$("#inputEmpName3").val();
		var rex= /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/
		if(rex.test(empname)){
			validate_state("#inputEmpName3","success","")
		}else{
			validate_state("#inputEmpName3","error","请输入数字或字母6~16位,或者2~5中文!")
		return false;
		}
		var empemail=$("#inputEmail3").val();
		var rexemail=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
		if(rexemail.test(empemail)){
			validate_state("#inputEmail3","success","")
		}else{
			validate_state("#inputEmail3","error","请输入正确的邮箱格式!")
			return false;
		}
		
		return true;
	}
	
	
	
	$("#save_emp").click(function(){
     	
     	 if(!validate()){
     		return ;
     	} 
     	
     	if($(this).attr("ajax-va")=="error"){
     		
     		return;
     	}
     	
	 	$.ajax({
			type:"POST",
			url:"${APP_PATH}/emp/addEmp.do",
			data:$("#addEmpModal form").serialize(),
			success:function(data){
				//前端校验完成后应该马上进行后端校验
				if(data.code==500){
					//检验邮箱有没有错误有错误就显示
					if(undefined!=data.extand.errors.email){
						validate_state("#emp_email input","error",data.extand.errors.email)
					}
					//员工姓名邮箱有没有错误有错误就显示
					if(undefined!=data.extand.errors.empName){
						validate_state("#emp_name input","error",data.extand.errors.empName)
					}
					return;
				}else{
				
				//1.关闭模态框(可以参考官方文档)
				$('#addEmpModal').modal('toggle')
				//2.显示响应结果
				confirm(data.msg);
				
				
				//3.跳转到最后一页我们设置了合理化所以就算是查询的页码大于本来的页码也会去到最后一页所以保险起见最好是原来的页码+1比较好，因为你不知道之前的数据是否正好是5的倍数
				ajax_toPage(page_Info.pages+1)
				
				}
			
			}
			
		})
     	
	})
	
	$("#edit_emp").click(function(){
		//当我们点击保存的时候也需要进行验证避免非法修改
		var empemail=$("#email3_update").val();
		var rexemail=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
		if(rexemail.test(empemail)){
			validate_state("#email3_update","success","")
		}else{
			validate_state("#email3_update","error","请输入正确的邮箱格式!")
			return false;
		}
		$.ajax({
			type:"PUT",
			url:"${APP_PATH}/rest/emp/upadteEmp/"+$(this).attr("edit-id"),
			data:$("#editEmpModal form").serialize(),
			success:function(data){
				//1.提示成功信息
				confirm(data.msg);
				//2.关闭模态框
				$('#editEmpModal').modal('toggle')
				
				//3.跳转到修改好的页面，不为别的只为局部刷新页面这样修改的数据可以立马看到
				ajax_toPage(currentPageNum)
			}
			
		})
		
	});
	
	
	/*
	全选全不选
	用attr去获取checked值是undifined
	因为我们压根没设置这个属性checked是dom原生的属性(true||false)
	我么们需要使用prop来修改和读取dom原生属性值
	*/
	$("#checkbox_all").click(function(){
		
		$(".checkbox_itme").prop("checked",$(this).prop("checked"));
	});
	
	//因为下面表格的checkbox是后面生成的属性也是后面生成的和弹出修改按钮的原理一样
	$(document).on("click",".checkbox_itme", function(){
		//判断下面选项是不是勾选了一页的大小如果是则勾选全选勾，如果不是则不要勾选全选勾
		//原生dom属性只要勾选就有checked属性，我们可以判断勾选的个数
		
		var flag=$(".checkbox_itme:checked").length==$(".checkbox_itme").length
			$("#checkbox_all").prop("checked",flag);		
	})
	
	$("#delete_batch").click(function(){
		var empNames="";
		var emp_ids="";
		$.each($(".checkbox_itme:checked"),function(){
			//索引是0开始的所以匹配的是第三个td也就是empName
			empNames+=$(this).parents("tr").find("td:eq(2)").text()+",";
			emp_ids+=$(this).parents("tr").find("td:eq(1)").text()+"-"
		});
		empNames=empNames.substring(0,empNames.length-1);
		emp_ids=emp_ids.substring(0,emp_ids.length-1);
		if(confirm("确认删除"+empNames+"吗？")){
			$.ajax({
				url:"${APP_PATH}/rest/emp/deleteEmp/"+emp_ids,
				type:"Delete",
				success:function(data){
					confirm(data.msg);
					ajax_toPage(currentPageNum);
					//alert(data.msg);
				}
			})
		}
		
	})
	

</script>
</html>