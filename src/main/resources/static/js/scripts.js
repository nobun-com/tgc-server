tjq(document).ready(function() {
    tjq("#price-range").slider({
        range: true,
        min: 0,
        max: 1000,
        values: [ 0, 1000 ],
        slide: function( event, ui ) {
            tjq(".min-price-label").val( "$" + ui.values[ 0 ]);
            tjq(".max-price-label").val( "$" + ui.values[ 1 ]);
        }
    });
    tjq(".min-price-label").val( "$" + tjq("#price-range").slider( "values", 0 ));
    tjq(".max-price-label").val( "$" + tjq("#price-range").slider( "values", 1 ));
});




function onsearch() {
    var val = jQuery.trim(jQuery(this).val()).replace(/ +/g, ' ').toLowerCase();
    jQuery('.search_item').show().filter(function() {
        var text = jQuery('.searchee',this).text().replace(/\s+/g, ' ').toLowerCase();
        return !~text.indexOf(val);
    }).hide();
}

jQuery(function() {
	jQuery(this).on('keyup', '.search', onsearch)
	jQuery('body').trigger('reload');
});

function closeLogin(){
	jQuery('#travelo-login').hide();
	jQuery("#soap-popupbox").hide();
}

function closeSignUp(){
	jQuery('#travelo-signup').hide();
	jQuery("#soap-popupbox").hide();
}

function clearErrorSpan(){
	setTimeout(function() {
		jQuery('#notfind').hide();
		jQuery('#login-email').val("");
		jQuery('#login-password').val("");
		jQuery('#signup-email').val("");
		jQuery('#signup-password').val("");
		jQuery('#signup-name').val("");
		jQuery('#signup-confirmPassword').val("");
		jQuery('#signup-mobile').val("");
		jQuery('#signup-refrralCode').val("");
	},100);
	
}

function applyCoupon(id) {
	var couponCode = jQuery(".couponCode"+id).val();
	jQuery.ajax({
		  type: "POST",
		  url: "/applyCoupon",
		  data: {
		  		couponCode : couponCode,
		  		userCartId : id
		  },
		  cache: false,
		  success: function(data){
				console.log(data);
				jQuery(".message"+id).text(data.message);
				if(data.success) {
					jQuery(".cost"+id).text('$' + data.cost);
					jQuery(".total-cost").text('$' + data.totalCost);
				}
		  },
		  error: function(data){
			  	jQuery(".message"+id).text("Unable to apply coupon");
		  }
		});
}


function signup() {
	var confirmPassword = jQuery('#signup-confirmPassword').val();
	var password = jQuery('#signup-password').val();

	if(password != confirmPassword) {
		jQuery('#signup-error-span').html('<p id="notfind">Password and Confirm password dosent matches</p>');
		return;
	}

	var email = jQuery('#signup-email').val();
	var name = jQuery('#signup-name').val();
	var mobile = jQuery('#signup-mobile').val();
	var gender = jQuery('#signup-gender>option:selected').val();
	
	var data = {
			"email" : email,
			"name" : name,
			"gender" : gender,
			"password" : password,
			"phone" : mobile,
	}
	
	jQuery.ajax({
		  type: "POST",
		  url: "/openRegister",
		  data: data,
		  cache: false,
		  success: function(data){
				jQuery('#login-error-span').html('<p id="notfind">' + data + '</p>');
				window.location.href = "/profile"
		  },
		  error: function(data){
				jQuery('#signup-error-span').html('<p id="notfind">' + data.responseJSON.message + '</p>');
		  }
		});
}

function login() {
	var password = jQuery('#login-password').val();
	var email = jQuery('#login-email').val();
	var redirectUrl = jQuery('#login-redirect-url').val();
	
	var data = {
			"email" : email,
			"password" : password,
	}
	
	jQuery.ajax({
		  type: "POST",
		  url: "/openLogin",
		  data: data,
		  cache: false,
		  success: function(data){
				jQuery('#login-error-span').html('<p id="notfind">' + data + '</p>');
				if(redirectUrl) {
					window.location.href = "/"+redirectUrl
				} else {
					window.location.href = "/profile"
				}
		  },
		  error: function(data){
				jQuery('#login-error-span').html('<p id="notfind">' + data.responseJSON.message + '</p>');
		  }
		});
}


function filterClass(date, displayDate) {

	jQuery(".selected-date").text(displayDate);
    jQuery('.match_class').show().filter(function() {
    	if(date=='All') {
    		return false;
    	}
    	var value = jQuery('.filter-date',this).text();
        return date!=value;
    }).hide();
}