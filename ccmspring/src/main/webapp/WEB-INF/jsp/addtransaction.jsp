<%@include file="init.jsp"%>

<html>
<body>
 <spring:message code="transaction.add.success.msg" var="successMesssage"/>
	<!-- <script type="text/javascript">
 	var result="${result}";
 	if(result != "" && result ==  "success"){
 	 	alert('${successMesssage}');
			window.close();
 	 	}
 </script> -->
<div class="modal fade" id="add-transaction-modal" tabindex="-1" role="dialog" aria-labelledby="add-transaction" aria-hidden="true" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content" id="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="myModalLabel1">
					<spring:message code="transaction.add.btn" />
				</h3>
			</div>
			<spring:message code="transaction.transaction.date" var="transactionDate" />

			<form:form method="POST" modelAttribute="transactionForm" action="saveTransaction" cssClass="form-inline" id="transaction-form">
				<div class="modal-body">
					<form:errors path="*" cssClass="error" element="div" />
					<div class="form-group" id="errors"></div>
					<div>
						<label class="custom-control-label"><spring:message code="customer.tab" /></label>
						<div class="custom-controls">
							<form:select path="customerName" cssClass="form-control">
								<form:option value="-1" label="Select One" />
								<form:options items="${customerList}" itemValue="id" itemLabel="fullName" />
							</form:select>
						</div>
					</div>
					<div>
						<label class="custom-control-label"><spring:message code="transaction.dialog.credit.card" /></label>
						<div class="custom-controls">
							<form:select path="creditCardId" cssClass="form-control">
								<form:option value="-1" label="Select One" />
							</form:select>
						</div>
					</div>
					<div>
						<label class="custom-control-label">${transactionDate}</label>
						<div class="custom-controls">
							<form:input path="transactionDate" placeholder="${transactionDate} *" cssClass="form-control date-picker" />
						</div>
					</div>
					<div>
						<label class="custom-control-label"><spring:message code="transaction.amount" /></label>
						<div class="custom-controls">
							<form:input path="amount" cssClass="form-control" />
						</div>
					</div>
					<div>
						<label class="custom-control-label"><spring:message code="transaction.description" /></label>
						<div class="custom-controls">
							<form:textarea path="description" cssClass="form-control" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" onclick="hidemodalDialog('transaction-modal-div', 'add-transaction-modal')">
						<spring:message code="btn.cancel" />
					</button>

					<button type="submit" class="btn btn-success">
						<spring:message code="transaction.save.btn.label" />
					</button>
					<input type="hidden" value="${error}" id="form-error"/>
				</div>
			</form:form>
			<!-- Pass error message from language properties to java script  -->
			<script type="text/javascript">
				var errors = new Array();
				errors['customerName.error'] = "<spring:message code='transaction.validation.transaction.select.customer' javaScriptEscape='true' />";
				errors['creditCardId.error'] = "<spring:message code='transaction.validation.transaction.select.credit.card' javaScriptEscape='true' />";
				errors['transactionDate.error'] = "<spring:message code='transaction.validation.transaction.date' javaScriptEscape='true' />";
				errors['amount.error'] = "<spring:message code='transaction.validation.transaction.amount' javaScriptEscape='true' />";
			</script>
		</div>
	</div>
</div>
</body>
</html>
