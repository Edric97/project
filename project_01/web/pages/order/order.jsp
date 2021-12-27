<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <%@include file="/pages/common/header.jsp" %>
    <script type="text/javascript">
        $(function () {
            var tmpDate = new Date(timestamp);
        });
    </script>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">我的订单</span>
    <%@include file="/pages/common/login_success_menu.jsp"%>
</div>

<div id="main">

    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>状态</td>
        </tr>

        <c:forEach items="${myOrder}" var="order">
            <tr>
                <td>${order.timestamp.toString()}</td>
                <td>${order.amount}</td>
                <td>
                    <c:choose>
                        <c:when test="${order.status == 0}">
                            未发货
                        </c:when>
                        <c:when test="${order.status == 1}">
                            已发货
                        </c:when>
                        <c:otherwise>
                            已收货
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>


    </table>


</div>

<%@include file="/pages/common/footer.jsp" %>
</body>
</html>