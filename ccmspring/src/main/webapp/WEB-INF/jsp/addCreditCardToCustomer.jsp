<%@include file="init.jsp"%>

<html>
<spring:message code="customer.success.add.customer.card" var="successMesssage" />
<body>
	<!-- <script type="text/javascript">
		var result = "${result}";
		if (result != "" && result == "success") {
			alert('${successMesssage}');
			window.close();
		}
	</script> -->
<div class="modal fade" id="customer-credit-card-modal" tabindex="-1" role="dialog" aria-labelledby="addCustomerCreditCard" aria-hidden="true" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content" id="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="myModalLabel1">
					<spring:message code="add.customer.credit.card" />
				</h3>
			</div>


			<form:form method="POST" modelAttribute="creditCardForm" action="saveCreditCard" cssClass="form-inline" id="credit-card-form">

				<div class="modal-body">
					<spring:message code="customer.dialog.cardnumber" var="cardnumber" />
					<spring:message code="customer.dialog.name" var="name" />
					<spring:message code="customer.dialog.cvv" var="cvv" />
					<spring:message code="customer.dialog.validFrom" var="validFrom" />
					<spring:message code="customer.dialog.validTo" var="validTo" />

					<form:errors path="*" cssClass="error" element="div" />
					<div class="form-group" id="errors"></div>
					<div class="form-group">
						<div class="row">
							<label class="col-sm-3 control-label"><spring:message code="customer.tab" /></label>
							<div class="col-sm-9">
								<form:select path="cardHolderId">
									<form:option value="-1" label="Select One" />
									<form:options items="${userList}" itemValue="id" itemLabel="fullName" />
								</form:select>
							</div>
						</div>
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
					<button type="button" class="btn btn-danger" onclick="hidemodalDialog('customer-credit-card-modal-div', 'customer-credit-card-modal')">
						<spring:message code="btn.cancel" />
					</button>
					<button type="submit" class="btn btn-success">
						<spring:message code="customer.dialog.save.changes.label" />
					</button>
				</div>
				<input type="hidden" value="${error}" id="form-error"/>
			</form:form>
			<!-- Pass error message from language properties to java script  -->
			<script type="text/javascript">
				var errors = new Array();
				errors['cardHolderId.error'] = "<spring:message code='customer.validation.card.select.customer' javaScriptEscape='true' />";
				errors['cardNumber.error'] = "<spring:message code='customer.validation.creditcard.details.cardnumber' javaScriptEscape='true' />";
				errors['nameOnCard.error'] = "<spring:message code='customer.validation.creditcard.details.nameoncard' javaScriptEscape='true' />";
				errors['ccv.error'] = "<spring:message code='customer.validation.creditcard.details.cvv' javaScriptEscape='true' />";
				errors['validFromDate.error'] = "<spring:message code='customer.validation.creditcard.details.validFrom' javaScriptEscape='true' />";
				errors['validToDate.error'] = "<spring:message code='customer.validation.creditcard.details.validTo' javaScriptEscape='true' />";
			</script>
		</div>
	</div>
</div>
</body>
</html>
