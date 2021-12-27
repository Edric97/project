<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: caoxiaodong
  Date: 2021/12/26
  Time: 下午1:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <c:if test="${page.pageNo > 1}">
        <a href="${pageContext.request.contextPath}${page.url}1">首页</a>
        <a href="${pageContext.request.contextPath}${page.url}${page.pageNo-1}">上一页</a>
    </c:if>

    <c:choose>
        <c:when test="${page.pageTotal <= 5}">
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${page.pageTotal}"></c:set>
        </c:when>
        <c:when test="${page.pageTotal > 5}">
            <c:choose>
                <c:when test="${page.pageNo <= 3}">
                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="5"></c:set>
                </c:when>
                <c:when test="${page.pageNo > page.pageTotal - 3}">
                    <c:set var="begin" value="${page.pageTotal - 4}"></c:set>
                    <c:set var="end" value="${page.pageTotal}"></c:set>
                </c:when>
                <c:otherwise>
                    <c:set var="begin" value="${page.pageNo - 2}"></c:set>
                    <c:set var="end" value="${page.pageNo + 2}"></c:set>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i != page.pageNo}">
            <a href="${pageContext.request.contextPath}${page.url}${i}">${i}</a>
        </c:if>
    </c:forEach>

    <c:if test="${page.pageNo < page.pageTotal}">
        <a href="${pageContext.request.contextPath}${page.url}${page.pageNo+1}">下一页</a>
        <a href="${pageContext.request.contextPath}${page.url}${page.pageTotal}">末页</a>
    </c:if>

    共${page.pageTotal}页，${page.pageTotalCount}条记录
    到第<input value="${page.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">

    <script type="text/javascript">
        $(function () {
            $("#searchPageBtn").click(function () {

                var pageNo = $("#pn_input").val();

                var pageTotal = ${page.pageTotal};

                if (pageNo < 1 || pageNo > pageTotal) {
                    alert("请输入可到达的页码！");
                    $("#pn_input").val(${page.pageNo});
                    return false;
                }
                location.href = "${pageScope.basePath}${page.url}" + pageNo;

            });
        });
    </script>
</div>