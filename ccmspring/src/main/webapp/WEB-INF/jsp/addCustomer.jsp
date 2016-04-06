<%@include file="init.jsp"%>
<spring:htmlEscape defaultHtmlEscape="true"/>
<html>
<body>
	<spring:url value="/customer/editCreditCard" var="editCreditCardUrl" htmlEscape="true" />
	<spring:message code="customer.success.add.customer" var="successMesssage" />
	<c:if test="${isEdit}">
		<spring:message var="successMesssage" code="customer.success.edit.customer" />
	</c:if>
<spring:message var="deleteMesssage" code="customer.card.delete.confirmation.dlg.message" />
<spring:message var="deleteSuccessMesssage" code="customer.delete.credit.card.info.message" />

	<script type="text/javascript">
		$(document).ready(
				function() {
					jQuery(document).on("click", ".removeCreditCard", function(e){
						var anchor = $(this);
						var txt;
						var r = confirm('${deleteMesssage}');
						if (r == true) {
							$.get("deleteCreditCard/" + anchor.attr("id"),
									function(data, status) {
										if (data) {
											alert('${deleteSuccessMesssage}');
											anchor.parents("tr").remove();
										}
									});
						}

						
						
					});
				});
	</script>
<div class="modal fade" id="add-customer-modal" tabindex="-1" role="dialog" aria-labelledby="add-customer" aria-hidden="true" data-keyboard="false" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content" id="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="myModalLabel">
					<c:choose>
						<c:when test="${isEdit}">
							<spring:message code="edit.customer" />
						</c:when>
						<c:otherwise>
							<spring:message code="add.customer" />
						</c:otherwise>
					</c:choose>
				</h3>
			</div>
			<div class="modal-body">
				<spring:message code="customer.dialog.firstname" var="firstname" />
				<spring:message code="customer.dialog.lastname" var="lastname" />
				<spring:message code="customer.dialog.dob" var="dob" />
				<spring:message code="customer.dialog.email" var="email" />
				<spring:message code="customer.dialog.phone" var="phone" />
				<spring:message code="customer.dialog.housename" var="housename" />
				<spring:message code="customer.dialog.street" var="street" />
				<spring:message code="customer.dialog.area" var="area" />
				<spring:message code="customer.dialog.zip" var="zip" />
				<spring:message code="customer.dialog.menu.country" var="country" />
				<spring:message code="customer.dialog.menu.state" var="state" />
				<spring:message code="customer.dialog.menu.city" var="city" />
				<spring:message code="customer.dialog.cardnumber" var="cardnumber" />
				<spring:message code="customer.dialog.name" var="name" />
				<spring:message code="customer.dialog.cvv" var="cvv" />
				<spring:message code="customer.dialog.validFrom" var="validFrom" />
				<spring:message code="customer.dialog.validTo" var="validTo" />
				<spring:message code="customer.dialog.menu.country" var="country" />
				<spring:message code="customer.dialog.menu.state" var="state" />
				<spring:message code="customer.dialog.menu.city" var="city" />

				<form:form method="POST" id="customer-form" modelAttribute="customerForm" action="saveCustomer" enctype="multipart/form-data"
					cssClass="form-inline">
					<form:errors path="*" cssClass="error" element="div" />
					<div class="form-group" id="errors"></div>
					<h4>Basic Details</h4>
					<div class="form-group">
						<form:input path="user.firstName" placeholder="${firstname}" cssClass="form-control" />
						<form:input path="user.lastName" placeholder="${lastname}" cssClass="form-control" />
					</div>
					<div class="form-group">
						<form:input id="birthDate" path="user.birthDate" placeholder="${dob}" cssClass="form-control date-picker" />
						<form:input path="user.email" placeholder="${email}" cssClass="form-control" />
						<form:input id="phoneNumber" path="user.phoneNumber" placeholder="${phone}" cssClass="form-control" />
					</div>
					<div class="form-group">
						<c:url var="profilePicPlaceHolder" value="/resources/images/placeholder-customer.png" />
						<c:if test="${imagePath != null && imagePath != ''}">
							<c:set var="profilePicPlaceHolder" value="${imagePath}" />
						</c:if>
						<img width="80" height="80" src="${profilePicPlaceHolder}" class="img-circle img-responsive"
							style="display: inline-block;">
						<div class="file-upload"><form:input path="profilePicture" type="file" /></div>
					</div>
					<hr />
					<h4>Address Details</h4>
					<div class="form-group">
						<form:input path="address.houseName" cssClass="form-control" placeholder="${housename}" />
						<form:input path="address.street" cssClass="form-control" placeholder="${street}" />
						<form:input path="address.area" cssClass="form-control" placeholder="${area}" />
						<form:input path="zipCode" placeholder="${zip}" cssClass="form-control" />

					</div>
					<div class="form-group">

						<form:select path="geographic.country" id="country" cssClass="form-control">
							<form:option value="" label="${country}" />
							<form:options items="${customerForm.countryList}" />
						</form:select>
						<form:select path="geographic.state" id="state" cssClass="form-control">
							<form:option value="" label="${state}" />
							<form:options items="${customerForm.stateList}" />
						</form:select>
						<form:select path="geographic.city" id="city" cssClass="form-control">
							<form:option value="" label="${city}" />
							<form:options items="${customerForm.cityList}" />
						</form:select>
					</div>
					<hr />
					<h4>Credit Card Details</h4>
					<div class="form-group">
						<c:choose>
							<c:when test="${fn:length(customerForm.creditCards) gt 0}">


								<table class="table table-responsive table-bordered table-striped table-condensed">
									<thead>
										<tr class="info">
											<td><spring:message code="customer.cardnumber" /></td>
											<td><spring:message code="customer.creditlimit" /></td>
											<td><spring:message code="customer.availablecredit" /></td>
											<td></td>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="creaditCardDetail" items="${customerForm.creditCards }">
											<tr>
												<td>${creaditCardDetail.cardNumber }</td>
												<td>${creaditCardDetail.creditLimit }</td>
												<td>${creaditCardDetail.availableCreditLimit }</td>
												<td><a href="#" id="${creaditCardDetail.id }" class="removeCreditCard"><spring:message
															code="select.action.delete" /></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>

							</c:when>
							<c:otherwise>
								<div class="form-group">
									<form:input id="cardNumber" path="creditCard.cardNumber" cssClass="form-control" placeholder="${cardnumber}" />
									<form:input path="creditCard.nameOnCard" cssClass="form-control" placeholder="${name}" />
									<form:input id="cvv" path="creditCard.cvv" cssClass="form-control" placeholder="${cvv}" />

								</div>
								<div class="form-group">
									<form:input id="validFromDate" path="creditCard.validFromDate" cssClass="form-control"
										placeholder="${validFrom}" />
									<form:input id="validToDate" path="creditCard.validToDate" cssClass="form-control" placeholder="${validTo}" />
								</div>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" onclick="hidemodalDialog('customer-modal-div' , 'add-customer-modal')">
							<spring:message code="btn.cancel" />
						</button>
						<input type="submit" value='<spring:message  code="customer.dialog.save.changes.label"/>' class="btn btn-success" />
					</div>
					<input type="hidden" value="${error}" id="form-error"/>
				</form:form>
				<!-- Pass error message from language properties to java script  -->
				<script type="text/javascript">
					var errors = new Array();
					errors['firstName.error'] = "<spring:message code='customer.validation.basic.details.firstname' javaScriptEscape='true' />";
					errors['lastName.error'] = "<spring:message code='customer.validation.basic.details.lastname' javaScriptEscape='true' />";
					errors['birthDate.error'] = "<spring:message code='customer.validation.basic.details.birthdate' javaScriptEscape='true' />";
					errors['email.error'] = "<spring:message code='customer.validation.basic.details.email' javaScriptEscape='true' />";
					errors['invalid.email.error'] = "<spring:message code='customer.validation.basic.details.email.invalid' javaScriptEscape='true' />";
					errors['phoneNumber.error'] = "<spring:message code='customer.validation.basic.details.phone' javaScriptEscape='true' />";
					errors['houseName.error'] = "<spring:message code='customer.validation.address.details.housenumber' javaScriptEscape='true' />";
					errors['zipCode.error'] = "<spring:message code='customer.validation.geographic.details' javaScriptEscape='true' />";
					errors['cardNumber.error'] = "<spring:message code='customer.validation.creditcard.details.cardnumber' javaScriptEscape='true' />";
					errors['nameOnCard.error'] = "<spring:message code='customer.validation.creditcard.details.nameoncard' javaScriptEscape='true' />";
					errors['ccv.error'] = "<spring:message code='customer.validation.creditcard.details.cvv' javaScriptEscape='true' />";
					errors['validFromDate.error'] = "<spring:message code='customer.validation.creditcard.details.validFrom' javaScriptEscape='true' />";
					errors['validToDate.error'] = "<spring:message code='customer.validation.creditcard.details.validTo' javaScriptEscape='true' />";
				</script>
			</div>
		</div>
	</div>
</div>
</body>
</html>
