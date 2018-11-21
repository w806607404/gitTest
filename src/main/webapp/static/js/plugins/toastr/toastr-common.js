var Toastr = {
	warning:function(message,timeout,bool){
		toastr.options = {
				  "closeButton": bool,
				  "debug": false,
				  "progressBar": true,
				  "positionClass": "toast-top-center",
				  "onclick": null,
				  "showDuration": "400",
				  "hideDuration": "1000",
				  "timeOut": timeout,
				  "extendedTimeOut": "1000",
				  "showEasing": "swing",
				  "hideEasing": "linear",
				  "showMethod": "fadeIn",
				  "hideMethod": "fadeOut"
				};
		toastr.warning(message);
	},
	error:function(message,timeout,bool){
		toastr.options = {
				  "closeButton": bool,
				  "debug": false,
				  "progressBar": true,
				  "positionClass": "toast-top-center",
				  "onclick": null,
				  "showDuration": "400",
				  "hideDuration": "1000",
				  "timeOut": timeout,
				  "extendedTimeOut": "1000",
				  "showEasing": "swing",
				  "hideEasing": "linear",
				  "showMethod": "fadeIn",
				  "hideMethod": "fadeOut"
				};
		toastr.error(message);
	},
	success:function(message,timeout,bool){
		toastr.options = {
				  "closeButton": bool,
				  "debug": false,
				  "progressBar": true,
				  "positionClass": "toast-top-center",
				  "onclick": null,
				  "showDuration": "400",
				  "hideDuration": "1000",
				  "timeOut": timeout,
				  "extendedTimeOut": "1000",
				  "showEasing": "swing",
				  "hideEasing": "linear",
				  "showMethod": "fadeIn",
				  "hideMethod": "fadeOut"
				};
		toastr.success(message);
	}
};

					
				        