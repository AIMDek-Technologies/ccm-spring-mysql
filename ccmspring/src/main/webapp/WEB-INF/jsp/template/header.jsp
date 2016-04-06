<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>

<%@include file="../init.jsp"%>


<html>
<body>
	<c:set var="exception" value="SPRING_SECURITY_LAST_EXCEPTION" />
	<c:set var="exceptionMessage" value="${sessionScope[exception].message}" />
	<div class="container-fluid">
		<div class="row" id="header">
			<div class="col-md-2">
				<img id="logo" src="<c:url value="/resources/images/aimdek-logo.png" />">
			</div>	
			<div class="col-md-10">
				<sec:authorize access="isAnonymous()">
					<div class="col-md-5 col-md-offset-7">
						<spring:url value="/j_spring_security_check" var="loginUrl" />
						<form:form action="${loginUrl}" commandName="user" mathod="post">
							<div class="form-group">
								<spring:message code="email.address" var="email" />
								<spring:message code="password" var="password" />
								<table border="0" width="90%">
									<tr>
										<td><form:input path="email" size="30" placeholder="${email}" cssClass="form-control" /></td>
										<td><form:password path="password" size="30" placeholder="${password}" cssClass="form-control" /></td>
										<td><input type="submit" name="submit" value='<spring:message code="login.label"/>'
											class="btn btn-success"></td>
									</tr>
									<tr>
										<%-- <td colspan="3"><form:errors path="email" cssClass="error" /><br /> <form:errors path="password"
												cssClass="error" /></td> --%>
										<c:if test="${not empty exceptionMessage && error}">
											<p class="alert alert-danger">
												<spring:message code="${exceptionMessage}" />
											</p>
										</c:if>
									</tr>
								</table>
							</div>
						</form:form>
					</div>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<spring:url value="/j_spring_security_logout" var="logout" htmlEscape="true" />
					<div class="col-md-11">
						<div style="float: right;">
							<h2>Welcome <c:out value="${sessionScope.user.fullName }"/>
								<a href="${logout}" class="btn btn-danger"><spring:message code="logout.label" /></a>
							</h2>
						</div>
					</div>
				</sec:authorize>
			</div>

		</div>

	</div>

	<sec:authorize access="isAuthenticated()">
		<div class="container">
			<ul class="nav nav-pills nav-justified">
				<li role="presentation" class="${page == 'home' ? 'active':'' }"><spring:url value="/home" var="homeUrl" htmlEscape="true" /> <a href="${homeUrl}"><spring:message
							code="home.tab" /></a></li>
				<sec:authorize access="hasRole('HELPDESK') and isAuthenticated()">
					<li role="presentation" class="${page == 'customer' ? 'active':'' }"><spring:url value="/customer/" var="customerUrl" htmlEscape="true" /> <a
					href="${customerUrl}"><spring:message code="customer.tab" /></a></li>
				</sec:authorize>
				
				<li role="presentation" class="${page == 'statement' ? 'active':'' }"><spring:url value="/statement/" var="statementUrl" htmlEscape="true" /> <a
					href="${statementUrl}"><spring:message code="statement.tab" /></a></li>

				<li role="presentation" class="${page == 'transactions' ? 'active':'' }"><spring:url value="/transactions/" var="transactionsUrl" htmlEscape="true" /> <a
					href="${transactionsUrl}"><spring:message code="transactions.tab" /></a></li>
				<sec:authorize access="hasRole('HELPDESK') and isAuthenticated()">
					<li role="presentation" class="${page == 'bulkupload' ? 'active':'' }"><spring:url value="/bulkupload/" var="bulkuploadUrl" htmlEscape="true" /> <a
						href="${bulkuploadUrl}"><spring:message code="bulkupload.tab" /></a></li>
				</sec:authorize>

			</ul>
		</div>
	</sec:authorize>
</body>
</html>
