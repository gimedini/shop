<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h3>카테고리</h3>

<ul>
	<li><a href="${pageContext.request.contextPath}/shop/list?c=100&l=1">상의</a>
	
		<ul class="low">
			<li><a href="${pageContext.request.contextPath}/shop/list?c=101&l=2">티셔츠</a></li>
			<li><a href="${pageContext.request.contextPath}/shop/list?c=102&l=2">남방</a></li>
			<li><a href="${pageContext.request.contextPath}/shop/list?c=103&l=2">맨투맨</a></li>
			<li><a href="${pageContext.request.contextPath}/shop/list?c=104&l=2">후드</a></li>
		</ul>
		
	</li>
		
	<li><a href="${pageContext.request.contextPath}/shop/list?c=200&l=1">치마</a></li>
	<li><a href="${pageContext.request.contextPath}/shop/list?c=300&l=1">바지</a></li>
	<li><a href="${pageContext.request.contextPath}/shop/list?c=400&l=1">악세사리</a></li>
</ul>
