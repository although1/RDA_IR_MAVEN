<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">遥控信息列表</h3>
<form method="post" action="${pageContext.request.contextPath}/findIrFuncByIrnameServlet">
    <!--下拉菜单这里选中的遥控名称，才应该是下面要刷新的遥控 -->
    <select class="form-control" name="irname" >
    <c:forEach items="${irname}" var="str" varStatus="s">
        <option value="${str.irname}" <c:if test="${str.irname eq irListName}">selected</c:if> >${str.irname}</option>
    </c:forEach>
</select>
    <div class="form-group" style="float: right; margin: 5px; text-align: center;">
        <input class="btn btn btn-primary" type="submit" value="查询">
    </div>
</form>
    <div style="float: right;margin: 5px;">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/findIrListByPageServlet">快速查询</a>
    </div>
    <table border="1"  class="table table-bordered table-hover">
        <tr class="success" >
            <th>遥控头码</th>
            <th>遥控功能</th>
            <th>遥控码值</th>
        </tr>
        <c:forEach items="${ir_funcInfoByName}" var="user" varStatus="s">
            <tr>
                <td>${user.header_code}</td>
                <td>${user.remote_function}</td>
                <td>${user.remote_code}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
