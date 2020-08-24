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
   <button class="btn btn-primary">新增</button>
   <button class="btn btn-danger">删除</button>
   </div>
   </div>
   <!-- 显示表格数据 -->
   <div class="row"> 
    <div class="col-md-12">  
   <table class="table table-hover">
   <tr>
   <th>员工编号</th>
   <th>员工姓名</th>
   <th>员工性别</th>
   <th>员工邮箱</th>
   <th>所处部门</th>
   <th>操作</th>
   </tr>
   <c:forEach items="${pageInfo.list}" var="emp">
   <tr>
   <td>${emp.empId }</td>
   <td>${emp.empName }</td>
   <td>${emp.gender==1?"男":"女"}</td>
   <td>${emp.email }</td>
   <td>开发部</td>
   <td>
	<button class="btn btn-info btn-sm">
	<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
	编辑
	</button>
	<button class="btn btn-danger btn-sm">
	<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
	删除
	</button>
	</td>
   </tr>
   </c:forEach>
   
   </table>
    </div>
   </div>
   <!-- 分页行 -->
   <div class="row">
   <!-- 分页信息 -->
     <div class="col-md-6">
     当前 第：${pageInfo.pageNum } 页 &emsp; 有${pageInfo.pages}页  &emsp; 一共有：${pageInfo.total }条记录
     </div>
     <!-- 分页条 -->
       <div class="col-md-6">
       <nav aria-label="Page navigation">
  <ul class="pagination">
  <li> <a href="${APP_PATH }/emp/findAll.do?pn=1">首页</a></li>
	  <c:if test="${pageInfo.hasPreviousPage}">
	    <li>
	      <a href="${APP_PATH }/emp/findAll.do?pn=${pageInfo.pageNum -1}" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	      </a>
	    </li>
	    </c:if>
    <c:forEach items="${pageInfo.navigatepageNums }" var="num">
	    <c:if test="${num == pageInfo.pageNum }">
	    	<li class="active"><a href="${APP_PATH }/emp/findAll.do?pn=${num}">${num}</a></li>
	   </c:if>
	     <c:if test="${num != pageInfo.pageNum }">
	   		 <li><a href="${APP_PATH }/emp/findAll.do?pn=${num}">${num}</a></li>
	   </c:if>
   </c:forEach>
   <c:if test="${pageInfo.hasNextPage}">
    <li>
      <a href="${APP_PATH }/emp/findAll.do?pn=${pageInfo.pageNum+1}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
   </c:if>
    <li><a href="${APP_PATH }/emp/findAll.do?pn=${pageInfo.pages}">尾页</a></li>
  </ul>
</nav>
       
       </div>
   </div>
   </div>
</body>
</html>