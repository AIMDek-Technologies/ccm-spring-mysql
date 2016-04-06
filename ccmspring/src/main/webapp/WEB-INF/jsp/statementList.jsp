<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@page isELIgnored="false"%>
<t:layout>
	<jsp:attribute name="header">
		<jsp:include page="template/header.jsp"></jsp:include>
	</jsp:attribute>
	<jsp:attribute name="footer">
		<jsp:include page="template/footer.jsp"></jsp:include>	
	</jsp:attribute>
	<jsp:body>
	
<spring:url value="filter" var="filterUrl" htmlEscape="true" />
<c:url var="imagesUrl" value="/resources/images/pdf.png" />

<html>
<head>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var statementsTable = $('#statements')
								.dataTable(
										{
											"bLengthChange" : false,
											"bProcessing" : true,
											"bServerSide" : true,
											"bSortCellsTop" : true,
											"sDom" : '<"top"l>rt<"bottom"ip><"clear">',
											"bSortClasses" : false,
											"sPaginationType" : "full_numbers",
											"sAjaxSource" : "${filterUrl}",
											"columns" : [
													{
														"data" : "statementId"
													},
													{
														"data" : "statementDate",
													},
													{
														"data" : "cardNumber"
													},
													{
														"data" : "fromDate",
													},
													{
														"data" : "toDate",
													},
													{
														"data" : "amountDue"
													},
													{
														"data" : "dueDate",
													},
													{
														"data" : "statementId",
														"render" : function(
																data, type,
																full, meta) {
															if (full.documentReference != ''
																	&& full.documentReference != null) {
																return '<a href="download/'
										+ data
										+ '"><img src="${imagesUrl}" class="pdf"></a>';
															} else {
																return '';
															}
														}
													} ],
											"aoColumnDefs" : [ {
												"bSortable" : false,
												"aTargets" : [ 7 ]
											} ]
										});
						statementsTable.columnFilter({
							aoColumns : [ null, null, {
								type : "text"
							}, null, null, null, null ]

						});
					});
</script>
</head>
<body>
<div class="container">
<div class="reports">
<div class="reports" style="background-color: #fff;">

 		<table class="table table-responsive table-striped table-bordered table-hover" id="statements">
 		<thead>
			<tr class="info">
				<th><spring:message code="statement.id" /></th>
				<th><spring:message code="statement.date" /></th>
				<th><spring:message code="statement.card.number" /></th>
				<th><spring:message code="statement.from.date" /></th>
				<th><spring:message code="statement.to.date" /></th>
				<th><spring:message code="statement.amount.due" /></th>
				<th><spring:message code="statement.due.date" /></th>
				<th><spring:message code="statement.action" /></th>
			</tr>
			</thead>
			<tfoot>
			<tr class="info">
				<th></th>
				<th></th>
				<th><spring:message code="statement.card.number" /></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
			</tfoot>
		</table>
		</div>
		</div>
		</div>
</body>
</html>
	</jsp:body></t:layout>