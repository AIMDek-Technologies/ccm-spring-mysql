$(document).ready(
		function() {

			$("#birthDate").mask('99/99/9999');
			
			$('#phoneNumber').mask("(999)-999-9999", {
				placeholder : "(___)-___-____"
			});
			
			$('#zipCode').mask("999999", {
				placeholder : "______"
			});
			
			$('#cardNumber').mask("9999-9999-9999-9999", {
				placeholder : "____-____-____-____"
			});
			
			$('#cvv').mask("999", {
				placeholder : "___"
			});

			$("#validFromDate").datepicker(
					{
						dateFormat : 'dd/mm/yy',
						onSelect : function(selectedDate) {
							$("#validToDate").datepicker("option", "minDate",
									selectedDate);
						}
					});

			$("#validToDate").datepicker(
					{
						dateFormat : 'dd/mm/yy',
						onSelect : function(selectedDate) {
							$("#validFromDate").datepicker("option", "maxDate",
									selectedDate);
						}
					});

			$(".date-picker").datepicker({
				dateFormat : 'dd/mm/yy',
				maxDate : 'today'
			});

			$("input#zipCode").change(function() {
				$.getJSON("getCountryList", {
					zipCode : $(this).val()
				}, function(j) {
					if (j.id != null) {
						$("select#country").val(j.country);
						$("select#state").val(j.state);
						$("select#city").val(j.city);
					} else {
						$("select#country").val("");
						$("select#state").val("");
						$("select#city").val("");
					}

				});
			});

			jQuery(document).on("change", "select#customerName", function(e){
						$.getJSON("getCreditCards", {
							customerId : $(this).val()
						}, function(j) {
							var options = '';
							if (j.length > 0) {
								for (var i = 0; i < j.length; i++) {
									options += '<option value="' + j[i].id
											+ '">' + j[i].cardNumber
											+ '</option>';
								}
							} else {
								options = '<option>--- Select ---</option>';
							}
							$("select#creditCardId").html(options);
						});
					});
			
			jQuery.validator.addMethod('selectRequired', function (value) {
			    return (value !== '-1');
			}, "This field is required");
			
			jQuery.validator.addMethod('amountRequired', function (value) {
			    return (value >  0);
			}, "This field is required");

			/* end */
		});

function openAddEditCustomerModal(url){
	 $.ajax({
	        url:  url,
	        async : false,
	        success: function(data){
	            $('#customer-modal-div').append(data);
	            $('#add-customer-modal').modal('show');
	            setModalAtCenter('#add-customer-modal');
	            $('#errors').empty();
	            validateCustomer();
	        }  
	    });  
	}

function validateCustomer(){
	// Validate customer form.
	$('#customer-form').validate({
		errorElement: 'div',
		//set this to false to validate on submit only
        onkeyup: false,
        onfocusout: false,
		//place all errors in a <div id="errors"> element
		errorPlacement: function(error, element) {
			error.appendTo("div#errors");
		}, 
		rules: {
			"user.firstName" : {required: true},
			"user.lastName" : {required: true},
			"user.birthDate" : {required: true},
			"user.email" : {required: true,  email: true},
			"user.phoneNumber" : {required: true},
			"zipCode" : {required: true},
			"address.houseName" : {required: true},
			"creditCard.cardNumber" : {required: true},
			"creditCard.nameOnCard" : {required: true},
			"creditCard.ccv" : {required: true},
			"creditCard.validFromDate" : {required: true},
			"creditCard.validToDate" : {required: true},
		}, 
		submitHandler: function(form) {
			var $form = $(form);
			if ($('#profilePicture').val() == "") { 
				$('div.file-upload').remove();
			}
			var formData = new FormData($form[0]);
			$.ajax({
				type : $form.attr('method'),
				url : $form.attr('action'),
				data : formData,
				processData: false,
			    contentType: false,
				success : function(callback) {
					$('#customer-modal-div').empty().append(callback);
					if ($("#form-error").val()) {
						$('#add-customer-modal').modal('show');
						setModalAtCenter('#add-customer-modal');
						validateCustomer();
					}else {
						$('#add-customer-modal').modal('hide');
						$('#customer-modal-div').empty();
						location.reload();
					}
				},
				error : function() {
				}
			});
			return false;
	    }, messages: {
			"user.firstName" : errors['firstName.error'],
			"user.lastName" : errors['lastName.error'],
			"user.birthDate" : errors['birthDate.error'],
			"user.email": {required: errors['email.error'], email: errors['invalid.email.error']},
			"user.phoneNumber" : errors['phoneNumber.error'],
			"address.houseName" : errors['houseName.error'],
			"zipCode" : errors['zipCode.error'],
			"creditCard.cardNumber" : errors['cardNumber.error'],
			"creditCard.nameOnCard" : errors['nameOnCard.error'],
			"creditCard.ccv" : errors['ccv.error'],
			"creditCard.validFromDate" : errors['validFromDate.error'],
			"creditCard.validToDate" : errors['validToDate.error'],
		}
	});
}

function hidemodalDialog(selector, modalId){
     $('#' + modalId).modal('hide');
     $('#' + selector).empty();
     $('#ui-datepicker-div').remove();
}

function openAddTransactionModal(url){
 $.ajax({
        url:  url,
        async : false,
        success: function(data){
            $('#transaction-modal-div').empty().append(data);
            $('#add-transaction-modal').modal('show');
            setModalAtCenter('#add-transaction-modal');
            validateTransaction();
        }  
    });  
}

function validateTransaction(){
	// Validate transaction form.
	$('#transaction-form').validate({
		errorElement: 'div',
		//set this to false to validate on submit only
        onkeyup: false,
        onfocusout: false,
		//place all errors in a <div id="errors"> element
		errorPlacement: function(error, element) {
			error.appendTo("div#errors");
		}, 
		rules: {
			customerName: {selectRequired : true},
			creditCardId : {selectRequired : true},
			transactionDate : {required: true},
			amount : {amountRequired: true},
		}, 
		submitHandler: function(form) {
			var $form = $(form);
			var formData = new FormData($form[0]);
			$.ajax({
				type : $form.attr('method'),
				url : $form.attr('action'),
				data : formData,
				processData: false,
			    contentType: false,
				success : function(callback) {
					$('#transaction-modal-div').empty().append(callback);
					if ($("#form-error").val()) {
						$('#add-transaction-modal').modal('show');
						setModalAtCenter('#add-transaction-modal');
						validateTransaction();
					}else {
						$('#add-transaction-modal').modal('hide');
						$('#transaction-modal-div').empty();
						location.reload();
					}
				},
				error : function() {
				}
			});
			return false;
	    }, messages: {
			customerName: {selectRequired: errors['customerName.error']},
			creditCardId: {selectRequired: errors['creditCardId.error']},
			transactionDate : {required: errors['transactionDate.error']},
			amount : {amountRequired : errors['amount.error']}
		}
	});
}

function openAddCustomerCreditCardModal(url){
	 $.ajax({
	        url:  url,
	        async : false,
	        success: function(data){
	            $('#customer-credit-card-modal-div').append(data);
	            $('#customer-credit-card-modal').modal('show');
	            setModalAtCenter('#customer-credit-card-modal');
	            validateCustomerCreditCard();
	        }  
	    });  
	}

function validateCustomerCreditCard(){
	// Validate customer credit card form.
	$('#credit-card-form').validate({
		errorElement: 'div',
		//set this to false to validate on submit only
//        onkeyup: false,
        onfocusout: false,
		//place all errors in a <div id="errors"> element
		errorPlacement: function(error, element) {
			error.appendTo("div#errors");
		}, 
		rules: {
			cardHolderId : {selectRequired : true},
			cardNumber : {required: true},
			nameOnCard : {required: true},
			cvv : {required: true},
			validFromDate : {required: true},
			validToDate : {required: true},
		}, 
		submitHandler: function(form) {
			var $form = $(form);
			var formData = new FormData($form[0]);
			$.ajax({
				type : $form.attr('method'),
				url : $form.attr('action'),
				data : formData,
				processData: false,
			    contentType: false,
				success : function(callback) {
					$('#customer-credit-card-modal-div').empty().append(callback);
					if ($("#form-error").val()) {
						$('#customer-credit-card-modal').modal('show');
						setModalAtCenter('#customer-credit-card-modal');
						validateCustomerCreditCard();
					}else {
						$('#customer-credit-card-modal').modal('hide');
						$('#customer-credit-card-modal-div').empty();
						location.reload();
					}
				},
				error : function() {
				}
			});
			return false;
	    },messages: {
			cardHolderId: {selectRequired: errors['cardHolderId.error']},
			cardNumber : errors['cardNumber.error'],
			nameOnCard : errors['nameOnCard.error'],
			ccv : errors['ccv.error'],
			validFromDate : errors['validFromDate.error'],
			validToDate : errors['validToDate.error'],
		}
	});
}

function openBulkUploadModal(url){
	$.ajax({
        url:  url,
        success: function(data){
            $('#bulk-upload-div').append(data);
            $('#bulk-upload-modal').modal('show');
            setModalAtCenter('#bulk-upload-modal');
            // Validate bulk upload form.
            $('#bulk-upload-form').validate({
            	rules: {
            		file : {required : true}
            	}, 
        		submitHandler: function(form) {
        			var $form = $(form);
        			var formData = new FormData($form[0]);
        			$.ajax({
        				type : $form.attr('method'),
        				url : $form.attr('action'),
        				data : formData,
        				processData: false,
        			    contentType: false,
        				success : function(callback) {
        						location.reload();
        				},
        				error : function() {
        				}
        			});
        			return false;
        	    },
            	messages: {
            		file: {required: "Please select file"},
        		}
            });
        }  
    });  
}

function setModalAtCenter(element){
	var modal = $(element);
	var dialog = modal.find('.modal-dialog');  
	dialog.css("margin-top", Math.max(0, ($(window).height() - dialog.height()) / 2));
}