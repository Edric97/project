<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%@include file="/pages/common/header.jsp" %>
    <script type="text/javascript">
        $(function () {
            $(".book_add").click(function () {
                if (${empty sessionScope.userId}) {
                    alert("亲，请您先登录🙂");
                    location.href = "${pageContext.request.contextPath}/pages/user/login.jsp";
                } else {
                    var bookId = $(this).attr("bookId");
                    window.location.href = "${pageContext.request.contextPath}/cart/add?bookId=" + bookId;
                }
            });
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">网上书城</span>
    <div>
        <%-- 尚未登陆的情况 --%>
        <c:if test="${empty sessionScope.user}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>
        <%-- 已经登陆的情况 --%>
        <c:if test="${not empty sessionScope.user}">
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
            <a href="${pageContext.request.contextPath}/order/showMyOrder?userId=${sessionScope.userId}">我的订单</a>
            <a href="${pageContext.request.contextPath}/user/logout">注销</a>&nbsp;&nbsp;
        </c:if>

        <a href="${pageContext.request.contextPath}/cart/exhibit">购物车</a>
        <a href="pages/manager/manager.jsp">后台管理</a>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="${pageContext.request.contextPath}/client/pageByPrice" method="get">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="hidden" name="pageNo" value="${page.pageNo}">
                <input type="hidden" name="pageSize" value="4">
                <input type="submit" value="查询"/>
            </form>
        </div>
<%--        <div style="text-align: center">--%>
<%--            <span>您的购物车中有3件商品</span>--%>
<%--            <div>--%>
<%--                您刚刚将<span style="color: red">${bookName}</span>加入到了购物车中--%>
<%--            </div>--%>
<%--        </div>--%>

        <c:forEach items="${page.items}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${book.imgPath}"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <c:choose>
                        <c:when test="${book.stock > 0}">
                            <div class="book_add" bookId="${book.id}">
                                <button>加入购物车</button>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div>
                                掌柜的正在补货中！
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </c:forEach>


    </div>

    <%@include file="/pages/common/page_nav.jsp" %>

</div>

<%@include file="/pages/common/footer.jsp" %>
</body>
</html>