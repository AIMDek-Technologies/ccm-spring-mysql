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
<spring:url value="addTransaction" var="addTransactionUrl" htmlEscape="true" />

<html>
<head>
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						var transactionsTable = $('#transactions')
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
														"data" : "transactionId"
													},
													{
														"data" : "transactionDate",
													}, {
														"data" : "customerName"
													}, {
														"data" : "cardNumber"
													}, {
														"data" : "amount"
													}, {
														"data" : "balance"
													}, {
														"data" : "description"
													} ]
											,
											"fnCreatedRow" : function(nRow, aData, iDataIndex) {
												jQuery('td:eq(2)', nRow).text(aData.customerName);
												jQuery('td:eq(6)', nRow).text(aData.description);
												
											}
										});
						transactionsTable.columnFilter({
							aoColumns : [ null, null, {
								type : "text"
							}, {
								type : "text"
							}, null, null, {
								type : "text"
							} ]

						});

					});
</script>
</head>
<body>
<div class="container">
<div class="reports">
<div align="right">
 <%-- <input  class="btn btn-default" type="button" value="<spring:message code="transaction.add.btn" />"
					onclick="window.open('${addTransactionUrl}',null,'scrollbars=yes, resizable=yes, left=300, width=800, height=500');">--%>
					<button type="button" class="btn btn-default" data-toggle="modal" onclick="openAddTransactionModal('${addTransactionUrl}')">
					 	<spring:message code="transaction.add.btn" />
					</button>
					</div> 
<div class="reports" style="background-color: #fff;">
		<table class="table table-responsive table-striped table-bordered table-hover" id="transactions">
		<thead>
	<tr class="info">
				<th><spring:message code="transaction.id" /></th>
				<th><spring:message code="transaction.transaction.date" /></th>
				<th><spring:message code="transaction.customer.name" /></th>
				<th><spring:message code="transaction.credit.card.number" /></th>
				<th><spring:message code="transaction.amount" /></th>
				<th><spring:message code="transaction.balance" /></th>
				<th><spring:message code="transaction.description" /></th>
			</tr>
			</thead>
				<tfoot>
			<tr class="info">
				<th></th>
				<th></th>
				<th><spring:message code="transaction.customer.name" /></th>
				<th><spring:message code="transaction.credit.card.number" /></th>
				<th></th>
				<th></th>
				<th><spring:message code="transaction.description" /></th>
			</tr>
			</tfoot>
		</table>
		</div>
		</div>
		</div>
		<div id="transaction-modal-div"></div>
</body>
</html>
	</jsp:body></t:layout>