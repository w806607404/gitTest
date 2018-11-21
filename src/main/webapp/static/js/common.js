
/**
 * 工具组件 对原有的工具进行封装，自定义某方法统一处理
 * jesse Shen 2016.07.10
 */
;
(function() {
	sn = {};
	sn.ajax = (function(params) {
		layer.load(2);
		var pp = {
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				layer.closeAll('loading');
//				if(XMLHttpRequest.responseText.indexOf("<!DOCTYPE html>") > 0){
//					top.location.href="/login.shtml";
//				}
			}
		};
		$.extend(pp, params);
		$.ajax(pp);
	});
	sn.ajaxSubmit = (function(form, params) {// form 表单ID. params ajax参数
		layer.load(2);
		var pp = {
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				layer.closeAll('loading');
//				if(XMLHttpRequest.responseText.indexOf("<!DOCTYPE html>") > 0){
//					top.location.href="/login.shtml";
//				}
			}
		};
		$.extend(pp, params);
		$(form).ajaxSubmit(pp);
	});
	CommnUtil = {
		/**
		 * ajax同步请求 返回一个html内容 dataType=html. 默认为html格式 如果想返回json.
		 * CommnUtil.ajax(url, data,"json")
		 * 
		 */
		ajax : function(url, data, dataType) {
			if (!CommnUtil.notNull(dataType)) {
				dataType = "html";
			}
			var html = '没有结果!';
			// 所以AJAX都必须使用ly.ajax..这里经过再次封装,统一处理..同时继承ajax所有属性
			sn.ajax({
				type : "post",
				data : data,
				url : url,
				dataType : dataType,// 这里的dataType就是返回回来的数据格式了html,xml,json
				async : false,
				cache : false,// 设置是否缓存，默认设置成为true，当需要每次刷新都需要执行数据库操作的话，需要设置成为false
				success : function(data) {
					layer.closeAll('loading');
					html = data;
				}
			});
			return html;
		},
		/**
		 * 判断某对象不为空..返回true 否则 false
		 */
		notNull : function(obj) {
			if (obj === null) {
				return false;
			} else if (obj === undefined) {
				return false;
			} else if (obj === "undefined") {
				return false;
			} else if (obj === "") {
				return false;
			} else if (obj === "[]") {
				return false;
			} else if (obj === "{}") {
				return false;
			} else {
				return true;
			}

		},

		/**
		 * 判断某对象不为空..返回obj 否则 ""
		 */
		notEmpty : function(obj) {
			if (obj === null) {
				return "";
			} else if (obj === undefined) {
				return "";
			} else if (obj === "undefined") {
				return "";
			} else if (obj === "") {
				return "";
			} else if (obj === "[]") {
				return "";
			} else if (obj === "{}") {
				return "";
			} else {
				return obj;
			}

		},
		loadingImg : function() {
			var html = '<div class="alert alert-warning">'
					+ '<button type="button" class="close" data-dismiss="alert">'
					+ '<i class="ace-icon fa fa-times"></i></button><div style="text-align:center">'
					+ '<img src="' + rootPath + '/images/loading.gif"/><div>'
					+ '</div>';
			return html;
		},
		/**
		 * html标签转义
		 */
		htmlspecialchars : function(str) {
			var s = "";
			if (str.length == 0)
				return "";
			for (var i = 0; i < str.length; i++) {
				switch (str.substr(i, 1)) {
				case "<":
					s += "&lt;";
					break;
				case ">":
					s += "&gt;";
					break;
				case "&":
					s += "&amp;";
					break;
				case " ":
					if (str.substr(i + 1, 1) == " ") {
						s += " &nbsp;";
						i++;
					} else
						s += " ";
					break;
				case "\"":
					s += "&quot;";
					break;
				case "\n":
					s += "";
					break;
				default:
					s += str.substr(i, 1);
					break;
				}
			}
		},
		/**
		 * in_array判断一个值是否在数组中
		 */
		in_array : function(array, string) {
			for (var s = 0; s < array.length; s++) {
				thisEntry = array[s].toString();
				if (thisEntry == string) {
					return true;
				}
			}
			return false;
		}
	};
})();
// 表单json格式化方法……不使用&拼接
(function($) {
	$.fn.serializeJson = function() {
		var serializeObj = {};
		var array = this.serializeArray();
		$(array).each(
				function() {
					if (serializeObj[this.name]) {
						if ($.isArray(serializeObj[this.name])) {
							serializeObj[this.name].push(this.value);
						} else {
							serializeObj[this.name] = [
									serializeObj[this.name], this.value ];
						}
					} else {
						serializeObj[this.name] = this.value;
					}
				});
		return serializeObj;
	};
	
	//日期格式化
	Date.prototype.format = function(format) {
		var o = {
			"M+" : this.getMonth() + 1, // month
			"d+" : this.getDate(), // day
			"h+" : this.getHours(), // hour
			"m+" : this.getMinutes(), // minute
			"s+" : this.getSeconds(), // second
			"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
			"S" : this.getMilliseconds()// millisecond
		}
		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}
		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
						: ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return format;
	};
	
	
	TimeFormat = {
		timeparse : function(time) {
			//计算出相差天数
			var days=Math.floor(time/(24*3600*1000));
			//计算出小时数
			var leave1=time%(24*3600*1000)  ;  //计算天数后剩余的毫秒数
			var hours=Math.floor(leave1/(3600*1000));
			//计算相差分钟数
			var leave2=leave1%(3600*1000)  ;      //计算小时数后剩余的毫秒数
			var minutes=Math.floor(leave2/(60*1000));
			//计算相差秒数
			var leave3=leave2%(60*1000)  ;    //计算分钟数后剩余的毫秒数
			var seconds=Math.round(leave3/1000);
			if(days != 0){
				return days+" 天 "+hours+" 小时 "+minutes+" 分钟 "+seconds+" 秒";
			}else if(hours != 0){
				return hours+" 小时 "+minutes+" 分钟 "+seconds+" 秒";
			}else if(minutes != 0){
				return minutes+" 分钟 "+seconds+" 秒";
			}else{
				return seconds+" 秒";
			}
		}
	}
	
	$ScinanPageTable_ = (function(par){
		var confs = {
			dataField:"rows",
			search:false,
			pagination:true,
			pageSize:10,
			striped:true,
			clickToSelect:true,
			pageList:[5,10, 20, 50],
			sidePagination:'server',
			contentType:'application/x-www-form-urlencoded',
			dataType:'json',
			method:'post',
			searchAlign:'left',
			queryParamsType:'limit',
			searchOnEnterKey:false,
			showRefresh:true,
			showColumns:true,
			buttonsAlign:'right',
			toolbar:'#toolbar',
			toolbarAlign:'left',
			height:tableHeight(),
			queryParams:function getParams(params) {
				par.searchForm.find('select[name]').each(function () {
            		 if($.trim($(this).val()) != ''){
           	        	params[$(this).attr('name')] = $(this).val();
            		 }
           	      });
				par.searchForm.find('input[name]').each(function () {
            		 if($.trim($(this).val()) != ''){
           	        	params[$(this).attr('name')] = $(this).val();
            		 }
           	      });
           	      return params;
            },
            detailView: false,
            detailFormatter:function(index, row, element) {
				var html = '';
				$.each(row, function(key, val) {
					html += "<p>" + key + ":" + val + "</p>"
				});
				return html;
			}
		}
		
		var conf = $.extend(confs, par);
		
		var table = function(){
			$(window).resize(function() {
				conf.$id.bootstrapTable('resetView', {
			        height: tableHeight()
			    });
			});
			
			conf.$id.bootstrapTable(conf);
		}
		
		var refreshPage = function(){
			setTimeout(function(){
				conf.$id.bootstrapTable('destroy');
				table();
			},200);
		}
		
		var getSelectionIds = function(){
			var data = conf.$id.bootstrapTable('getSelections');
			var ids_arr = new Array();
			for(var i = 0;i<data.length;i++){
				ids_arr.push(data[i].id);
			}
			return ids_arr;
		}
		
		var getSelections = function(){
			return conf.$id.bootstrapTable('getSelections');
		}
		
		table();
		
		return {
				refreshPage : refreshPage,
				getSelectionIds:getSelectionIds,
				getSelections:getSelections
				};
		
	});
	
	//弹出框
	$ScinanSwal = {
		confirm:function(json,call){
			var json_ = json;
			json_.type = 'warning';
			json_.showCancelButton = true;
			json_.confirmButtonColor = "#DD6B55";
			json_.closeOnConfirm = false;
			swal(json_,call);
		},
		alert:function(msg1,msg2,type){
			swal(msg1,msg2,type);
		}
	}
	
	//日期控件可以自定义
	$ScinanDateTimePicker = {
		date:function(obj){
			var json_ = {};
			json_.weekStart = 1;
			json_.todayBtn = 1;
			json_.autoclose = 1;
			json_.todayHighlight = 1;
			json_.startView = 2;
			json_.minView = 2;
			json_.forceParse = 0;
			
			var conf = $.extend(json_, obj);
			
			$("#" + json_.id).datetimepicker(conf);
			$("#" + json_.id).blur(function(){
//				setTimeout(function(){
//					var date = $("#" + json_.id).val();
//					var reg_date = /^\d{4}-\d{1,2}-\d{1,2}/;
//					if(!reg_date.test(date)){
//						$("#" + json_.id).val("");
//					}
//				},200);
			});
		}
	}
	$TimeFormat = {//zh-CN    en_US
			timeparse : function(time,lan) {
				//计算出相差天数
				var days=Math.floor(time/(24*3600*1000));
				//计算出小时数
				var leave1=time%(24*3600*1000)  ;  //计算天数后剩余的毫秒数
				var hours=Math.floor(leave1/(3600*1000));
				//计算相差分钟数
				var leave2=leave1%(3600*1000)  ;      //计算小时数后剩余的毫秒数
				var minutes=Math.floor(leave2/(60*1000));
				//计算相差秒数
				var leave3=leave2%(60*1000)  ;    //计算分钟数后剩余的毫秒数
				var seconds=Math.round(leave3/1000);
				if(lan == 'zh-CN'){
					if(days != 0){
						return days+" 天 "+hours+" 小时 "+minutes+" 分钟 "+seconds+" 秒";
					}else if(hours != 0){
						return hours+" 小时 "+minutes+" 分钟 "+seconds+" 秒";
					}else if(minutes != 0){
						return minutes+" 分钟 "+seconds+" 秒";
					}else{
						return seconds+" 秒";
					}
				}else{
					if(days != 0){
						return days+" Day "+hours+" Hour "+minutes+" Minutes "+seconds+" Second";
					}else if(hours != 0){
						return hours+" Hour "+minutes+" Minutes "+seconds+" Second";
					}else if(minutes != 0){
						return minutes+" Minutes "+seconds+" Second";
					}else{
						return seconds+" Second";
					}
				}
			}
		}
	
	
	
	
	
	$ScinanSelect2 = (function(par){
		var formatState = function(state){
			if (!state.id) { return state.text; }
			var $state = $(
			'<span>' + state.text + '</span>'
			);
			return $state;
		}
		
		var confs = {
				dataField:"rows",
				templateResult: formatState,
				width:'100%',
				allowClear: true
			}
		
		var conf = $.extend(confs, par);
		$('#' + conf.id).select2(conf);
	});
	
	function tableHeight() {
		return $(window).height()-50;
	}
	
})(jQuery);



