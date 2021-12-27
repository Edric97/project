<%--
  Created by IntelliJ IDEA.
  User: caoxiaodong
  Date: 2021/12/25
  Time: 下午10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
    <a href="${pageContext.request.contextPath}/order/showMyOrder?userId=${sessionScope.userId}">我的订单</a>
    <a href="${pageContext.request.contextPath}/user/logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>