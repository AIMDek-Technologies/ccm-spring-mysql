<%@include file="init.jsp"%>

<html>
<body>
	<!-- <script type="text/javascript">
		var result = "${result}";
		var message = "${message}";
		if (result != "" && result == "success") {
			alert(message);
			window.close();
		}
	</script> -->
	<c:set var="msg" value="${type == 'Customer' ? 'bulk.upload.customer' : 'bulk.upload.transactions' }"></c:set>
<div class="modal fade" id="bulk-upload-modal" tabindex="-1" role="dialog" aria-labelledby="bulk-upload" aria-hidden="true" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content" id="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="myModalLabel1">
					<spring:message code="${msg}" />
				</h3>
			</div>

			<form method="POST" enctype="multipart/form-data" action="/ccmspring/bulkupload/uploadFile" id="bulk-upload-form">
				<div class="modal-body">
					<div class="form-group">
						File to upload: <input type="file" name="file" accept=".xls,.xlsx,.csv"> <input type="hidden" name="type" value="${type}">

					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" onclick="hidemodalDialog('bulk-upload-div', 'bulk-upload-modal')">
						<spring:message code="btn.cancel" />
					</button>
					<button type="submit" class="btn btn-success">
						<spring:message code="${msg}" />
					</button>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>
