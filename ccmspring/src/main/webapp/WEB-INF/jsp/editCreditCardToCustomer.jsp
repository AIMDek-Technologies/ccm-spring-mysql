<%@include file="init.jsp"%>

<html>
<spring:message code="customer.success.edit.customer.card" var="successMesssage" />
<body>
	<script type="text/javascript">
		var result = "${result}";
		if (result != "" && result == "success") {
			alert('${successMesssage}');
			window.close();
		}
	</script>

	<div class="modal-dialog">
		<div class="modal-content" id="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="myModalLabel1">
					<spring:message code="customer.dialog.edit.card.header" />
				</h3>
			</div>


			<form:form method="POST" modelAttribute="creditCardForm" action="saveCreditCard" cssClass="form-inline">

				<div class="modal-body">
					<spring:message code="customer.dialog.cardnumber" var="cardnumber" />
					<spring:message code="customer.dialog.name" var="name" />
					<spring:message code="customer.dialog.cvv" var="cvv" />
					<spring:message code="customer.dialog.validFrom" var="validFrom" />
					<spring:message code="customer.dialog.validTo" var="validTo" />

					<%-- <form:errors path="*" cssClass="errorblock" element="div" /> --%>

					<form:errors path="cardNumber" cssClass="error" />
					<form:errors path="nameOnCard" cssClass="error" />
					<form:errors path="cvv" cssClass="error" />
					<form:errors path="validFromDate" cssClass="error" />
					<form:errors path="validToDate" cssClass="error" />
					
					<div class="form-group">
						<h4>
							<spring:message code="customer.dialog.credit.card.detail" />
						</h4>
						<div class="form-group">
							<form:input id="cardNumber" path="cardNumber" placeholder="${cardnumber}" cssClass="form-control" />
							<form:input path="nameOnCard" placeholder="${name}" cssClass="form-control" />
							<form:input id="cvv" path="cvv" placeholder="${cvv}" cssClass="form-control" />
						</div>

						<div class="form-group">
							<form:input id="validFromDate" path="validFromDate" placeholder="${validFrom}" cssClass="form-control" />
							<form:input id="validToDate" path="validToDate" placeholder="${validTo}" cssClass="form-control" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" onclick="window.close()">
						<spring:message code="btn.cancel" />
					</button>
					<button type="submit" class="btn btn-success">
						<spring:message code="customer.dialog.save.changes.label" />
					</button>
				</div>
			</form:form>
		</div>


	</div>


</body>
</html>
