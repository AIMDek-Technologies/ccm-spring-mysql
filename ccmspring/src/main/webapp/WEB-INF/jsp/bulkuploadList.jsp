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
<spring:url value="openBulkUploadForm/Customer" var="openBulkUploadFormCustomerUrl" htmlEscape="true" />
<spring:url value="openBulkUploadForm/Transaction" var="openBulkUploadFormTransactionUrl" htmlEscape="true" />

<html>
<head>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var bulkUploadsTable = $('#bulkUploads')
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
														"data" : "bulkUploadId"
													},
													{
														"data" : "type"
													},
													{
														"data" : "uploadDate",
													},
													{
														"data" : "totalRecords"
													},
													{
														"data" : "successRecords"
													},
													{
														"data" : "failureRecords"
													}, {
														"data" : "uploadedBy"
													} ],
											"aoColumnDefs" : [ {
												"bSortable" : false,
												"aTargets" : [ 2 ]
											} ]
										});
						bulkUploadsTable.columnFilter({
							aoColumns : [ null, {
								type : "text"
							}, null, null, null, null, {
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

 <input class="btn btn-default" type="button" value="<spring:message code="bulk.upload.customer" />"
				onclick="openBulkUploadModal('${openBulkUploadFormCustomerUrl}');">
 <input class="btn btn-default" type="button" value="<spring:message code="bulk.upload.transactions" />"
				onclick="openBulkUploadModal('${openBulkUploadFormTransactionUrl}');">
</div>
<div class="reports" style="background-color: #fff;">
		<table class="table table-responsive table-striped table-bordered table-hover" id="bulkUploads">
		<thead>
			<tr class="info">
				<th><spring:message code="bulk.upload.id" /></th>
				<th><spring:message code="bulk.upload.type" /></th>
				<th><spring:message code="bulk.upload.date" /></th>
				<th><spring:message code="bulk.upload.record.processed" /></th>
				<th><spring:message code="bulk.upload.successfull.record" /></th>
				<th><spring:message code="bulk.upload.failure.records" /></th>
				<th><spring:message code="bulk.upload.by" /></th>
			</tr>
			</thead>
			<tfoot>
			<tr class="info">
				<th></th>
				<th><spring:message code="bulk.upload.type" /></th>
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
		<div id="bulk-upload-div"></div>
</body>
</html>
</jsp:body></t:layout>