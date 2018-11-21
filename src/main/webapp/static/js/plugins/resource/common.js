Date.prototype.format =function(format)
{
var o = {
"M+" : this.getMonth()+1, //month
"d+" : this.getDate(), //day
"h+" : this.getHours(), //hour
"m+" : this.getMinutes(), //minute
"s+" : this.getSeconds(), //second
"q+" : Math.floor((this.getMonth()+3)/3), //quarter
"S" : this.getMilliseconds() //millisecond
}
if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
(this.getFullYear()+"").substr(4- RegExp.$1.length));
for(var k in o)if(new RegExp("("+ k +")").test(format))
format = format.replace(RegExp.$1,
RegExp.$1.length==1? o[k] :
("00"+ o[k]).substr((""+ o[k]).length));
return format;
}

function check_mobile(str){
	re = /^1\d{10}$/
    if (re.test(str)) {
        return true;
    } else {
        alert("请输入正确的手机号码。");
        return false;
    }
}

function check_validateCode(str){
	re = /^\d{4}$/
    if (re.test(str)) {
        return true;
    } else {
        alert("请输入正确的验证码。");
        return false;
    }
}