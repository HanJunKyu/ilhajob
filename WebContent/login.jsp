<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ include file="header.jsp" %> --%>
<jsp:include page="header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title>일하잡 :: 로그인</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main2.css">
	</head>
	<body class="is-preload">
	 <c:if test="${not empty param.login}">
   	  		<c:if test="${not param.login}">
   	  			<script>alert("아이디 또는 비밀번호를 다시 확인해주세요.")</script>
   	  		</c:if>
   	  </c:if>
		<!-- Header -->

			<div id="login-container">
				<div class="login-wrapper" >
                    <div class="inner" >
                        <div class="login-title" >
                            <strong>로그인</strong>
                        </div>
                        <p class="join-ask">일하잡 회원이 아니면, 지금 <a href="join.html">회원가입</a>을 해주세요</p>
                        <ul class="actions">
                            <li><a href="${pageContext.request.contextPath}/user/UserLogin.us" id="user" class="login-selected">개인회원</a></li>
                            <li><a href="${pageContext.request.contextPath}/comp/CompLogin.co"  id="comp" class="login-not-selected">기업회원</a></li>
						</ul>
					</div>
                    <form name="userLoginForm" action="${pageContext.request.contextPath}/user/UserLoginOk.us" method="post" class="login-form">
                        <div class="login-input-div">

								<input type="text" name="user_id" id="name" value="" placeholder="아이디" />
								<input type="password" name="user_pw" id="email" value="" placeholder="비밀번호" />
                        </div>
                        <input onclick="userLoginForm.submit()" type="button" class="login-button" value="로그인">
                    </form>
                    <div class="login-find">
                        <a href="${pageContext.request.contextPath}/user/UserJoin.us"><strong>회원가입</strong></a>
                        <span style="padding: 0 4px;">|</span>
                        <a href="${pageContext.request.contextPath}/find.jsp" style="color: rgb(66, 66, 66); "><span>아이디/비밀번호 찾기</span></a>
                    </div>
                </div>
                <div style="padding-top: 21%" class="com-img">
                    <img src="${pageContext.request.contextPath}/images/com.png" alt="" width="330px">
                </div>
			</div>
			<div class="com-img2">				
                <img src="${pageContext.request.contextPath}/images/com2.png" alt="" width="750px" style="position: absolute; bottom: 0;">
			</div>

		<!-- <div class="push" style="height: 189px; margin-top: 50px;"></div>

	<div class="footer">
		<div class="footer-menu">
				<ul class="labeled-icons">
					<li>
						<span>회사소개</span>
					</li>
					<li>
						<span>광고문의</span>
					</li>
					<li>
						<span>이용약관</span> 
					</li>
					<li>
						<span>개인정보처리방침</span> 
					</li>
					<li>
						<span>고객센터</span> 
					</li>
				</ul>
		</div>
		<div class="footer-info">
				<ul class="labeled-icons">
					<li>
						<span>someone@untitled.tld</span>
					</li>
					<li style="padding-left: 0;">
						<span >(000) 000-0000 x12345</span>
					</li>
					<li style="padding-left: 0; margin-right: 15px;">
						<span>서울시 강남구 ooo - oo</span> 
					</li>
					<li style="margin-left: 10px;">
						<a href="#"><h4 class="icon brands fa-twitter"><span class="label">Twitter</span></h4></a>
					</li>
					<li>
						
						<a href="#"><h4 class="icon brands fa-facebook-f"><span class="label">Facebook</span></h4></a>
					</li>
				</ul>
		</div>
		<div class="copyright">
			Copyright ⓒ ILHAJOB Corp. All Right Reserved.
		</div>
	</div> 
-->
		<!-- Scripts -->
			<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
			<script src="${pageContext.request.contextPath}/assets/js/jquery.dropotron.min.js"></script>
			<script src="${pageContext.request.contextPath}/assets/js/jquery.poptrox.min.js"></script>
			<script src="${pageContext.request.contextPath}/assets/js/browser.min.js"></script>
			<script src="${pageContext.request.contextPath}/assets/js/breakpoints.min.js"></script>
			<script src="${pageContext.request.contextPath}/assets/js/util.js"></script>
			<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>

	</body>
</html>