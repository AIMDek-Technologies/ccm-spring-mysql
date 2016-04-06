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
<spring:url value="addCustomer" var="addCustomerUrl" htmlEscape="true" />
<spring:url value="addCreditCardToCustomer"
			var="addCreditCardToCustomerUrl" htmlEscape="true" />
<spring:url value="edit" var="editCustomerUrl" htmlEscape="true" />
<spring:message var="deleteMesssage"
			code="customer.delete.confirmation.dlg.message" />
<spring:message var="deleteSuccessMesssage"
			code="customer.delete.info.message" />
<html>
<head>
<script type="text/javascript">
	var customerListTable;
	$(document)
			.ready(
					function() {

						customerListTable = $('#customerList')
								.dataTable(
										{
											"bLengthChange" : false,
											"bProcessing" : true,
											"bServerSide" : true,
											"bSortCellsTop" : true,
											"sDom" : '<"top"l>rt<"bottom"ip><"clear">',
											"bSortClasses" : false,
											"sPaginationType" : "full_numbers",
											"sAjaxSource" : '${filterUrl}',
											"columns" : [
													{
														"data" : "customerId"
													},
													{
														"data" : "firstName"
													},
													{
														"data" : "lastName"
													},
													{
														"data" : "email"
													},
													{
														"data" : "phoneNumber"
													},
													{
														"data" : "generatedId",
														"render" : function(
																data, type,
																full, meta) {

															return '<button type="button" onclick="openAddEditCustomerModal('
															+ "'"
															+ '${editCustomerUrl}/'
															+ data
															+ "'"
															+ ');"'
															+ 'class="btn btn-default">Edit</button>'
															+ '<button type="button" id="'+ data
+ '" class="btn btn-danger removeCustomerCard">Delete</button>';

														}
													} ],
											"aoColumnDefs" : [ {
												"bSortable" : false,
												"aTargets" : [ 5 ]
											} ],
											"fnDrawCallback" : function(
													oSettings) {
												$(".removeCustomerCard")
														.click(
																function() {
																	var anchor = $(this);
																	var txt;
																	var r = confirm('${deleteMesssage}');
																	if (r == true) {
																		$
																				.get(
																						"deleteCustomer/"
																								+ anchor
																										.attr("id"),
																						function(
																								data,
																								status) {
																							if (data) {
																								alert('${deleteSuccessMesssage}');
																								customerListTable
																										.fnDeleteRow(anchor
																												.parents(
																														"tr")
																												.index());

																							}
																						});
																	}

																});
											},
											"fnCreatedRow" : function(nRow,
													aData, iDataIndex) {
												jQuery('td:eq(1)', nRow).text(
														aData.firstName);
												jQuery('td:eq(2)', nRow).text(
														aData.lastName);
												jQuery('td:eq(3)', nRow).text(
														aData.email);
											}
										});
						customerListTable.columnFilter({
							aoColumns : [ null, {
								type : "text"
							}, {
								type : "text"
							}, {
								type : "text"
							}, {
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
<button type="button" class="btn btn-default" onclick="openAddEditCustomerModal('${addCustomerUrl}')">
  <spring:message code="add.customer" />
</button>
 <input class="btn btn-default" type="button"
					value="<spring:message code="add.customer.credit.card" />"
					onclick="openAddCustomerCreditCardModal('${addCreditCardToCustomerUrl}');">
 </div>
     <div class="reports" style="background-color: #fff;">
 
 		<table
					class="table table-responsive table-striped table-bordered table-hover"
					id="customerList">
			<thead>
    <tr class="info">
				<th><spring:message code="customer.id" /></th>
				<th><spring:message code="customer.firstname" /></th>
				<th><spring:message code="customer.lastname" /></th>
				<th><spring:message code="customer.email" /></th>
				<th><spring:message code="customer.phone" /></th>
				<th><spring:message code="customer.actions" /></th>
			</tr>
			</thead>
			<tfoot>
			 <tr class="info">
				<th></th>
				<th><spring:message code="customer.firstname" /></th>
				<th><spring:message code="customer.lastname" /></th>
				<th><spring:message code="customer.email" /></th>
				<th><spring:message code="customer.phone" /></th>
				<th></th>
			</tr>
			</tfoot>
		</table>
		 </div>
		 </div>
		 </div>
		 <div id="customer-modal-div"></div>
		 <div id="customer-credit-card-modal-div"></div>
</body>
</html>
</jsp:body>
</t:layout>