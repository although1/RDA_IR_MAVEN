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
    <script>

        function checkHeaderCode(){
            var header_code = $("#header_code").val();
            var flag = false;
            if (header_code == ""){
                $("#header_code").css("border","1px solid  red");
            } else{
                flag = true;
                $("#header_code").css("border","");
            }
            return flag;
        }
        //入口函数
        $(function () {
            //提交时，检验头码有没有填入
            $("#search").submit(function () {
                return checkHeaderCode();
            });
            //光标移除后，检验头码又没输入
            $("#header_code").blur(checkHeaderCode);
        })
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">快速查询</h3>

    <form class="form-horizontal" id="search" action="${pageContext.request.contextPath}/findIrListByFindServlet" method="post">
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">遥控头码</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="header_code" name="header_code" value="${condition.header_code[0]}" id="inputEmail3" placeholder="00BF">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword" class="col-sm-2 control-label">功能</label>
            <div class="col-sm-5">
            <select class="form-control" name="remote_function">
                <c:forEach items="${remotefunction}" var="str" varStatus="s">
                    <option value="${str.remote_function}" >${str.remote_function}</option>
                </c:forEach>
            </select>
            </div>
            <div class="col-sm-5">
                <input type="text" class="form-control" name="remote_code"  value="${condition.remote_code[0]}" id="inputPassword" placeholder="01">
            </div>
        </div>
<%--        <div class="form-group">--%>
<%--            <label for="inputPassword3" class="col-sm-2 control-label">功能2</label>--%>
<%--            <div class="col-sm-5">--%>
<%--                <select class="form-control" >>--%>
<%--                    <c:forEach items="${remotefunction}" var="str" varStatus="s">--%>
<%--                        <option value="${str.remote_function}" >${str.remote_function}</option>--%>
<%--                    </c:forEach>--%>
<%--                </select>--%>
<%--            </div>--%>
<%--            <div class="col-sm-5">--%>
<%--                <input type="text" class="form-control"  value="${condition.remote_code[0]}" id="inputPassword3" placeholder="02">--%>
<%--            </div>--%>
<%--        </div>--%>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">查询</button>
            </div>
        </div>
    </form>
    <table border="1"  class="table table-bordered table-hover" style="margin: 5px">
        <tr class="success" >
            <th>遥控名称</th>
            <th>遥控头码</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${pb.list}" var="list" varStatus="s">
            <tr>
                <td>${list.irname}</td>
                <td>${list.irheader_code}</td>
                <td>
                    <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findIrFuncByIrnameServlet?irname=${list.irname}">查看</a>&nbsp;
                    <a class="btn btn-default btn-sm" href="img/${list.irname}.jpg">预览</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
<%--                如果当前页是首页，就隐藏箭头--%>
                <c:if test="${pb.currentPage == 1}">
                <li class="hide">
                </c:if>
<%--                如果当前页不是首页，就显示箭头--%>
                <c:if test="${pb.currentPage != 1}">
                <li>
                </c:if>

                    <a href="${pageContext.request.contextPath}/findIrListByPageServlet?currentPage=${pb.currentPage - 1}&rows=5&name=${condition.irheader_code[0]}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>


                <c:forEach begin="1" end="${pb.totalPage}" var="i" >

                    <c:if test="${pb.currentPage == i}">
                        <li class="active"><a href="${pageContext.request.contextPath}/findIrListByPageServlet?currentPage=${i}&rows=5&name=${condition.irheader_code[0]}">${i}</a></li>
                    </c:if>
                    <c:if test="${pb.currentPage != i}">
                        <li><a href="${pageContext.request.contextPath}/findIrListByPageServlet?currentPage=${i}&rows=5&name=${condition.irheader_code[0]}">${i}</a></li>
                    </c:if>

                </c:forEach>

<%--                如果当前页是最后一页，就把箭头隐藏--%>
                <c:if test="${pb.currentPage == pb.totalPage}">
                <li class="hide">
                </c:if>
                    <%-- 如果当前页不是最后一页，就显示箭头--%>
                <c:if test="${pb.currentPage != pb.totalPage}">
                <li>
                </c:if>

                    <a href="${pageContext.request.contextPath}/findIrListByPageServlet?currentPage=${pb.currentPage + 1}&rows=5&name=${condition.irheader_code[0]}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 25px;margin-left: 5px;">
                    共${pb.totalCount}条记录，共${pb.totalPage}页
                </span>

            </ul>
        </nav>


    </div>

</div>
</body>
</html>
