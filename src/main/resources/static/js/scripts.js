/*
 * Title:   Travelo | Responsive HTML5 Travel Template - Custom Javascript file
 * Author:  http://themeforest.net/user/soaptheme
 */

tjq(document).ready(function() {
    // UI Form Element
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
